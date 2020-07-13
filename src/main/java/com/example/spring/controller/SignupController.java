package com.example.spring.controller;

import java.util.*;

import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import com.example.spring.entity.*;
import com.example.spring.form.*;
import com.example.spring.service.*;
import com.example.spring.validation.group.*;

@Controller
@RequestMapping(SignupController.URI_PREFIX)
public class SignupController {

	public static final String URI_PREFIX = "/signup";

	public static final String TPL_PREFIX = "signup/";

	public static final String PATH_CREATE = "";

	public static final String PAGE_CREATE = "index";

	@Autowired
	protected UserService service;

	@Autowired
	protected RoleService roles;

	@ModelAttribute("roles")
	public List<Role> roles() {
		return roles.findAll();
	}

	/**
	 * create ページ用 (GET).
	 * 
	 * @param request
	 *                    リクエスト情報.
	 * @param form
	 *                    入力フォーム.
	 * 
	 * @return 画面表示用ワード（テンプレート、リダイレクト）.
	 */
	@GetMapping(PATH_CREATE)
	public String create(Model model, @ModelAttribute("form") UserForm form) {

		// 値の設定
		model.addAttribute("user", new User());
		return TPL_PREFIX + PAGE_CREATE;
	}

	/**
	 * create ページ用 (POST).
	 * 
	 * @param request
	 *                    リクエスト情報.
	 * @param form
	 *                    入力フォーム.
	 * @param result
	 *                    バリデーション結果
	 * 
	 * @return 画面表示用ワード（テンプレート、リダイレクト）.
	 */
	@PostMapping(PATH_CREATE)
	public String create(
			Model model,
			@Validated(Create.class) @ModelAttribute("form") UserForm form, BindingResult result,
			RedirectAttributes redirect) {

		// エラー判定
		if (result.hasErrors()) {
			// 値の設定
			model.addAttribute("user", new User());
			return TPL_PREFIX + PAGE_CREATE;
		}

		// 登録処理
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user = service.insert(user);

		// 更新結果をリダイレクト先に
		redirect.addFlashAttribute("success", "登録に成功しました。");
		return "redirect:/login";
	}

}
