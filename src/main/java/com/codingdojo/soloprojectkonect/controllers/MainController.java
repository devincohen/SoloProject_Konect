package com.codingdojo.soloprojectkonect.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.soloprojectkonect.models.HouseMember;
import com.codingdojo.soloprojectkonect.models.Household;
import com.codingdojo.soloprojectkonect.models.LoginUser;
import com.codingdojo.soloprojectkonect.models.User;
import com.codingdojo.soloprojectkonect.services.HouseMemberService;
import com.codingdojo.soloprojectkonect.services.HouseholdService;
import com.codingdojo.soloprojectkonect.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MainController {
	@Autowired
	private UserService userServ;
	
	@Autowired
	private HouseholdService houseServ;
	
	@Autowired
	private HouseMemberService memberServ;
	
	
	//register
	@GetMapping("/register")
	public String register(Model model, HttpSession session) {
		model.addAttribute("newUser", new User());
		return "register.jsp";
	}
	@PostMapping("/register")
	public String newReg(@Valid @ModelAttribute ("newUser") User newUser,
			BindingResult result, HttpSession session,
			Model model) {
		session.removeAttribute("loginErrors");
		User user = userServ.register(newUser, result);
		if(result.hasErrors()) {
			return "register.jsp";
		}
		userServ.addUser(user);
		session.setAttribute("id", user.getId());
		return "redirect:/dashboard";
	}
	
	
	
	//login
	@GetMapping("/login")
	public String login(Model model, HttpSession session) {
		model.addAttribute("newLogin", new LoginUser());
		return "login.jsp";
	}
	@PostMapping("/login")
	public String newLog(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
			BindingResult result, HttpSession session,
			Model model) {
		session.removeAttribute("loginErrors");
		User user = userServ.login(newLogin, result);
		if(result.hasErrors()) {
			session.setAttribute("loginErrors", result.getAllErrors());
			return "login.jsp";
		}
		model.addAttribute("user", user);
		session.setAttribute("id", user.getId());
		session.setAttribute("user", user);
		
		return "redirect:/dashboard";
		
	}
	
	
	//dashboard
	@GetMapping("/dashboard")
	public String success(Model model, HttpSession session) {
		if (session.getAttribute("id")==null) {
			return "redirect:/login";
		}
		User user = userServ.findUserById((Long)session.getAttribute("id"));
		List<Household> households = houseServ.allHouseholds();
		model.addAttribute("user", user);
		model.addAttribute("households", households);
		return "dashboard.jsp";
	}

	//add house
	@GetMapping("/new-connection")
	public String newHouse(Model model, HttpSession session) {
		if (session.getAttribute("id")==null) {
			return "redirect:/login";
		}
		model.addAttribute("newHouse", new Household());
		model.addAttribute("user", userServ.findUserById((Long)session.getAttribute("id")));
		return "addHouse.jsp";
	}
	@PostMapping("/addHouse")
	public String addHouse(@Valid @ModelAttribute("newHouse") Household household,
			BindingResult result, HttpSession session,
			Model model) {
		if (session.getAttribute("id")==null) {
			return "redirect:/login";
		}
		session.removeAttribute("houseErrors");
		if (result.hasErrors()) {
			session.setAttribute("houseErrors", result.getAllErrors());
			return "addHouse.jsp";
		}
		Household newHouse = houseServ.createHouse(household);
		Long id = newHouse.getId();
		session.setAttribute("household_id", newHouse.getId());
		model.addAttribute("id", id);
		return "redirect:/connection/"+id;
	}
	
	//view house, also lets you add house member
	@GetMapping("/connection/{id}")
	public String viewHouse(@PathVariable("id") Long houseId, Model model, HttpSession session ) {
		if (session.getAttribute("id")==null) {
			return "redirect:/login";
		}
		Household household = houseServ.findHouseById(houseId);
		User user = userServ.findUserById((Long) session.getAttribute("id"));
		Date today = new Date();
		model.addAttribute("today", today);
		model.addAttribute("household", household);
		model.addAttribute("newMember", new HouseMember());
		model.addAttribute("user", user);
		model.addAttribute("members", memberServ.findMembersInHouseholdById(houseId));
		return "viewHouse.jsp";
	}
	
	
	
	//edit house
	@GetMapping("/connection/{id}/edit")
	public String editHouseForm(Model model, HttpSession session, @PathVariable("id") Long houseId) {
		if (session.getAttribute("id")==null) {
			return "redirect:/login";
		}
		Household household = houseServ.findHouseById(houseId);
		model.addAttribute("household", household);
		return "editHouse.jsp";
	}
	@PutMapping("/connection/{id}/edit")
	public String editHouse(@Valid @ModelAttribute("household") Household household, BindingResult result, HttpSession session, Model model) {
		if (session.getAttribute("id")==null) {
			return "redirect:/login";
		}
		if(result.hasErrors()) {
			//session.setAttribute("memberErrors", result.getAllErrors());
			return "editHouse.jsp";
		}
		houseServ.updateHousehold(household);
		return "redirect:/dashboard";
	}
	
	//delete house
	@DeleteMapping("/connection/{id}/delete")
	public String deleteHouse(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("id")==null) {
			return "redirect:/login";
		}
		Household house = houseServ.findHouseById(id);
		if(house==null) {
			return "redirect:/dashboard";
		}
		if (!house.getHouseCreator().getId().equals(session.getAttribute("id"))) {
			return "redirect:/dashboard";
		}
		houseServ.deleteHousehold(house);
		return "redirect:/dashboard";
	}
	
	
	//add house member
	@PostMapping("/connection/{houseId}/add")
	public String addMember( @Valid @ModelAttribute("newMember") HouseMember newMember,  BindingResult result, HttpSession session, Model model) {
		if (session.getAttribute("id")==null) {
			return "redirect:/login";
		}
		//session.removeAttribute("memberErrors");
		if(result.hasErrors()) {
			session.setAttribute("memberErrors", result.getAllErrors());
			return "redirect:/connection/{houseId}";
		}
		memberServ.createMember(newMember);
		return "redirect:/connection/{houseId}";
	}
	
	
	
	//edit house member
	@GetMapping("/connection/{id}/edit/{memberId}")
	public String editMemberForm(@PathVariable("id") Long houseId, @PathVariable("memberId") Long member_id, Model model, HttpSession session) {
		model.addAttribute("household", houseServ.findHouseById(houseId));
		model.addAttribute("member", memberServ.findMemberById((Long) member_id));
		model.addAttribute("user", userServ.findUserById((Long) session.getAttribute("id")));
		return "editMember.jsp";
	}
	@PutMapping("/connection/{id}/edit/{member_id}")
	public String editMember(@Valid @ModelAttribute("member") HouseMember member,  BindingResult result, Model model, HttpSession session) {
		if (session.getAttribute("id")==null) {
			return "redirect:/login";
		}
		if(result.hasErrors()) {
			return "editMember.jsp";
		}
		memberServ.updateMember(member);
		return "redirect:/connection/{id}";
	}
	
	
	//delete house member
	@DeleteMapping("/connection/{household_id}/delete/{member_id}")
	public String deleteMember(@PathVariable("household_id") Long household_id, @PathVariable("member_id") Long member_id, Model model, HttpSession session) {
		if (session.getAttribute("id")==null) {
			return "redirect:/login";
		}
		HouseMember member = memberServ.findMemberById(member_id);
		if(member==null) {
			return "redirect:/connection/"+household_id;
		}
		memberServ.deleteMember(member);
		return "redirect:/connection/"+household_id;
	}
	
	
	
    //logout
	@GetMapping("/logout")
	public String logout(Model model, HttpSession session){
		session.invalidate();
        model.addAttribute("newLogin", new LoginUser());
		return "redirect:/login";
	}
}
