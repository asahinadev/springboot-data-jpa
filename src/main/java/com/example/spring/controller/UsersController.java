package com.example.spring.controller;

import java.util.*;

import javax.servlet.http.*;

import org.apache.commons.lang3.*;
import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;
import org.springframework.web.util.*;

import com.example.spring.entity.*;
import com.example.spring.form.*;
import com.example.spring.service.*;
import com.example.spring.validation.group.*;

import lombok.extern.slf4j.*;

@Slf4j
@Controller
@RequestMapping(UsersController.URI_PREFIX)
public class UsersController {

	public static final String URI_PREFIX = "/user";

	public static final String TPL_PREFIX = "user/";

	public static final String PAGE_INDEX = "index";

	public static final String PAGE_CREATE = "create";

	public static final String PAGE_MODIFY = "modify";

	public static final String PAGE_DELETE = "delete";

	public static final String PAGE_VIEW = "view";

	public static final String PATH_INDEX = "";

	public static final String PATH_CREATE = "/create";

	public static final String PATH_MODIFY = "/modify/{id}";

	public static final String PATH_DELETE = "/delete/{id}";

	public static final String PATH_VIEW = "/view/{id}";

	@Autowired
	protected UserService service;

	@Autowired
	protected RoleService roles;

	@ModelAttribute("roles")
	public List<Role> roles() {
		return roles.findAll();
	}

	@ModelAttribute("user")
	public User user(@PathVariable(value = "id", required = false) String id) {

		if (StringUtils.isNotEmpty(id)) {
			return service.findById(id);
		}
		return new User();

	}

	/**
	 * index ページ用.
	 * 
	 * @param request
	 *                    リクエスト情報.
	 * 
	 * @return 画面表示用ワード（テンプレート、リダイレクト）.
	 */
	@GetMapping(PATH_INDEX)
	public String index(HttpServletRequest request) {

		List<User> users = service.findAll();
		log.debug("{}", users);

		// 値の設定
		request.setAttribute("users", users);
		return TPL_PREFIX + PAGE_INDEX;

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
	public String create(@ModelAttribute("form") UserForm form) {

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
			@Validated(Create.class) @ModelAttribute("form") UserForm form, BindingResult result,
			RedirectAttributes redirect) {

		// エラー判定
		if (result.hasErrors()) {
			return TPL_PREFIX + PAGE_CREATE;
		}

		// 登録処理
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user = service.insert(user);
		log.debug("user {}", user);

		// 更新結果をリダイレクト先に
		redirect.addFlashAttribute("success", "登録に成功しました。");

		return "redirect:"
				+ UriComponentsBuilder.fromPath(URI_PREFIX + PATH_MODIFY).build(user.getId()).toASCIIString();
	}

	/**
	 * modify ページ用 (GET).
	 * 
	 * @param id
	 *                    識別用ID
	 * @param request
	 *                    リクエスト情報.
	 * @param form
	 *                    入力フォーム.
	 * 
	 * @return 画面表示用ワード（テンプレート、リダイレクト）.
	 */
	@GetMapping(PATH_MODIFY)
	public String modify(
			@ModelAttribute("user") User user,
			@ModelAttribute("form") UserForm form) {

		// 対象情報取得
		log.debug("user {}", user);

		// 値の設定
		BeanUtils.copyProperties(user, form);

		return TPL_PREFIX + PAGE_MODIFY;
	}

	/**
	 * modify ページ用 (POST).
	 * 
	 * @param id
	 *                    識別用ID
	 * @param request
	 *                    リクエスト情報.
	 * @param form
	 *                    入力フォーム.
	 * @param result
	 *                    バリデーション結果
	 * 
	 * @return 画面表示用ワード（テンプレート、リダイレクト）.
	 */
	@PostMapping(PATH_MODIFY)
	public String modify(
			@ModelAttribute("user") User user,
			@Validated(Save.class) @ModelAttribute("form") UserForm form, BindingResult result,
			RedirectAttributes redirect) {

		// 対象情報取得
		log.debug("user {}", user);

		// エラー判定
		if (result.hasErrors()) {
			return TPL_PREFIX + PAGE_MODIFY;
		}

		// 更新処理
		BeanUtils.copyProperties(form, user);
		user = service.update(user);
		log.debug("user {}", user);

		// 更新結果をリダイレクト先に
		redirect.addFlashAttribute("success", "更新に成功しました。");

		return "redirect:"
				+ UriComponentsBuilder.fromPath(URI_PREFIX + PATH_MODIFY).build(user.getId()).toASCIIString();
	}

	/**
	 * view ページ用 (GET).
	 * 
	 * @param id
	 *                    識別用ID
	 * @param request
	 *                    リクエスト情報.
	 * @param form
	 *                    入力フォーム.
	 * 
	 * @return 画面表示用ワード（テンプレート、リダイレクト）.
	 */
	@GetMapping(PATH_VIEW)
	public String view(
			@ModelAttribute("user") User user,
			@ModelAttribute("form") UserForm form) {

		// 対象情報取得
		log.debug("user {}", user);

		// 値の設定
		BeanUtils.copyProperties(user, form);

		return TPL_PREFIX + PAGE_VIEW;
	}

	/**
	 * delete ページ用 (GET).
	 * 
	 * @param id
	 *                    識別用ID
	 * @param request
	 *                    リクエスト情報.
	 * @param form
	 *                    入力フォーム.
	 * 
	 * @return 画面表示用ワード（テンプレート、リダイレクト）.
	 */
	@GetMapping(PATH_DELETE)
	public String delete(
			@ModelAttribute("user") User user,
			@ModelAttribute("form") UserForm form) {

		// 対象情報取得
		log.debug("user {}", user);

		// 値の設定
		BeanUtils.copyProperties(user, form);

		return TPL_PREFIX + PAGE_DELETE;
	}

	/**
	 * delete ページ用 (POST/DELETE).
	 * 
	 * @param id
	 *               識別用ID
	 * 
	 * @return 画面表示用ワード（テンプレート、リダイレクト）.
	 */
	@PostMapping(PATH_DELETE)
	@DeleteMapping(PATH_DELETE)
	public String delete(
			@ModelAttribute("user") User user,
			RedirectAttributes redirect) {

		// 対象情報取得
		log.debug("user {}", user);

		// 削除処理
		service.delete(user);

		// 更新結果をリダイレクト先に
		redirect.addFlashAttribute("success", "削除に成功しました。");

		return "redirect:"
				+ UriComponentsBuilder.fromPath(URI_PREFIX + PATH_INDEX).toUriString();
	}
}
