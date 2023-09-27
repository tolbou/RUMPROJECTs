package com.example.rum.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.rum.domain.dto.MyDto;
import com.example.rum.domain.dto.MyDtoUpdate;
import com.example.rum.domain.entity.MyEntity;
import com.example.rum.domain.service.Service.MyService;

@Controller
public class KanekomanController {
	/**
	 * ユーザー情報 Service
	 */
	@Autowired
	MyService myService;
	
	//login用
    @GetMapping("/")
    public String index() {
        return "user/index";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/general")
    public String general() {
        return "user/general";
    }

    @GetMapping("/admin")
    public String admin() {
        return "user/admin";
    }

	/**
	 * ユーザー情報一覧画面を表示
	 * @param model Model
	 * @return ユーザー情報一覧画面のHTML
	 */
	@GetMapping("/user/list")
	public String displayList(Model model) {
		List<MyEntity> userlist = myService.searchAll();
		model.addAttribute("userlist", userlist);
		return "user/list";
	}
	
	/**
	 * ユーザー新規登録画面を表示
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@RequestMapping("/user/add")
	public String displayAdd(Model model) {
		model.addAttribute("myDto", new MyDto());
		return "user/add";
	}

	/**
	 * ユーザー新規登録
	 * @param userRequest リクエストデータ
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@RequestMapping("/user/create")
	public String create(@Validated @ModelAttribute MyDto myDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			// 入力チェックエラーの場合
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "user/add";
		}
		// ユーザー情報の登録
		myService.create(myDto);
		return "redirect:/user/list";
	}
	
	/**
	 * ユーザー情報詳細画面を表示
	 * @param id 表示するユーザーID
	 * @param model Model
	 * @return ユーザー情報詳細画面
	 */
	@GetMapping("/user/{id}")
	public String displayView(@PathVariable Integer id, Model model) {
		MyEntity user = myService.findById(id);
		model.addAttribute("userData", user);
		return "user/view";

	}
	/**
	 * ユーザー編集画面を表示
	 * @param id 表示するユーザーID
	 * @param model Model
	 * @return ユーザー編集画面
	 */
	@GetMapping("/user/{id}/edit")
	public String displayEdit(@PathVariable Integer id, Model model) {
		MyEntity user = myService.findById(id);
		MyDtoUpdate myDtoUpdate = new MyDtoUpdate();
		myDtoUpdate.setId(user.getId());
		myDtoUpdate.setName(user.getName());
		myDtoUpdate.setPhone(user.getPhone());
		myDtoUpdate.setAddress(user.getAddress());
		model.addAttribute("myDtoUpdate", myDtoUpdate);
		return "user/edit";
	}
	/**
	 * ユーザー更新
	 * @param userRequest リクエストデータ
	 * @param model Model
	 * @return ユーザー情報詳細画面
	 */
	@RequestMapping("/user/update")
	public String update(@Validated @ModelAttribute MyDtoUpdate myDtoUpdate, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "user/edit";
		}
		// ユーザー情報の更新
		myService.update(myDtoUpdate);
		return String.format("redirect:/user/%d", myDtoUpdate.getId());
	}

	/**
	 * ユーザー情報削除
	 * @param id 表示するユーザーID
	 * @param model Model
	 * @return ユーザー情報詳細画面
	 */
	@GetMapping("/user/{id}/delete")
	public String delete(@PathVariable Integer id, Model model) {
		// ユーザー情報の削除
		myService.delete(id);
		return "redirect:/user/list";
	}
}
