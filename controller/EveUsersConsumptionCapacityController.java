package com.zlinks.controller;
import com.zlinks.domain/.EveUsersConsumptionCapacity;
import com.zlinks.service/.EveUsersConsumptionCapacityService;
import com.zlinks.common.web.BaseController;
import com.zlinks.common.web.JsonResult;
import com.zlinks.common.web.PageResult;
import com.zlinks.common.web.RestDoing;
import com.zlinks.Routes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import javax.servlet.http.HttpServletRequest;
/**
 * Copyright (C), 2017-2020, BBG
 * FileName: EveUsersConsumptionCapacityController
 * Author:   zhangjh
 * Date:     2018-7-9 16:20:13
 * Description: 控制层
 */
@RestController
@RequestMapping(value = Routes.API_VERSION)
public class EveUsersConsumptionCapacityController extends BaseController {

	/**
	 * logger 日志
	 */
    private Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	private EveUsersConsumptionCapacityService eveUsersConsumptionCapacityService;




	/**
     * @api {post} /eveUsersConsumptionCapacitys/save 01. EveUsersConsumptionCapacity删除
     * @apiPermission Login in Users
     * @apiGroup  EveUsersConsumptionCapacity
     * @apiVersion 1.0.1
	 * @apiParam {Number} id <code>必须参数</code> id
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     *     "code": 0,
     *     "data": 1
     *     "desc": "Success",
     *     "timestamp": "2018-7-9 16:20:13:082"
     * }
     * @apiErrorExample {json} Error-Response:
     * HTTP/1.1 200 OK
     * {
     *     "code": 110002,
     *     "desc": "Param is null or error",
     *     "timestamp": "2018-7-9 16:20:13:479"
     * }
     */
	@RequestMapping(value = "/eveUsersConsumptionCapacitys/delete/{id}", method = RequestMethod.DELETE)
	public JsonResult deleteJson(@PathVariable("id") int id) {
		RestDoing doing = jsonResult -> {

            int counts = eveUsersConsumptionCapacityServiceImpl.delete(eveUsersConsumptionCapacity);
            jsonResult.data = counts;
        };
        return doing.go(request, logger);
	}


	/**
	 * 详情页
	 *
	 * @param id 主键id
	 * @return String 详情页url
	 */
	@RequestMapping(value = "/eveUsersConsumptionCapacitys/info/{id}")
	public String info(@PathVariable("id") Long id) {

		RestDoing doing = jsonResult -> {

			EveUsersConsumptionCapacity entity  = eveUsersConsumptionCapacityServiceImpl.queryInfoById(id);
            jsonResult.data = entity;
        };
        return doing.go(request, logger);
	}

	/**
	 * 列表页面
	 * 
	 * @param findContent 搜索内容
	 * @param pageNo 页数
	 * @return Pagination 集合列表
	 */
	@RequestMapping(value = "list")
	public String list(String findContent, ModelMap modelMap, Integer pageNo) {
		try {
			Pagination<EveUsersConsumptionCapacity> data = eveUsersConsumptionCapacityService.findPage(modelMap, pageNo, pageSize);
			modelMap.addAttribute("data", data);
		} catch (Exception e) {
			logger.error(HPXSConstants.ERROR_STRING, e);
			redirect404();
		}
		return BASE_PATH + "list";
	}


	/**
     * @api {post} /eveUsersConsumptionCapacitys/save 01. EveUsersConsumptionCapacity新增
     * @apiPermission Login in Users
     * @apiGroup  EveUsersConsumptionCapacity
     * @apiVersion 1.0.1
	 * @apiParam {String} prop.propertyName <code>必须参数</code> EveUsersConsumptionCapacity的prop.propertyName
	 * @apiParam {Integer} prop.propertyName <code>必须参数</code> EveUsersConsumptionCapacity的prop.propertyName
     * @apiSuccessExample {json} Success-Response:
     * HTTP/1.1 200 OK
     * {
     *     "code": 0,
     *     "data": 1
     *     "desc": "Success",
     *     "timestamp": "2018-7-9 16:20:13:082"
     * }
     * @apiErrorExample {json} Error-Response:
     * HTTP/1.1 200 OK
     * {
     *     "code": 110002,
     *     "desc": "Param is null or error",
     *     "timestamp": "2018-7-9 16:20:13:479"
     * }
     */
	@RequestMapping(value = "/eveUsersConsumptionCapacitys/save", method = RequestMethod.POST)
	public JsonResult add(@RequestBody EveUsersConsumptionCapacity eveUsersConsumptionCapacity) {

  		RestDoing doing = jsonResult -> {

            int counts = eveUsersConsumptionCapacityServiceImpl.add(eveUsersConsumptionCapacity);
            jsonResult.data = counts;
        };
        return doing.go(request, logger);
	}


	/**
	 * 列表数据
	 * 
	 * @param findContent 搜索内容
	 * @param pageNo 页数
	 * @return Pagination 集合列表
	 */
	@RequestMapping(value = "jsonList")
	@ResponseBody
	public JsonResult<Pagination<EveUsersConsumptionCapacity>> jsonList(String findContent, ModelMap modelMap, Integer pageNo) {
		JsonResult<Pagination<EveUsersConsumptionCapacity>> result = new JsonResult<Pagination<EveUsersConsumptionCapacity>>();
		try {
			Pagination<EveUsersConsumptionCapacity> data = eveUsersConsumptionCapacityService.findPage(modelMap, pageNo, pageSize);
			result.setData(data);
		} catch (Exception e) {
			logger.error(HPXSConstants.ERROR_STRING, e);
			result.setCode(HPXSConstants.STATUS_ERROR);
			result.setMessage(e.getMessage());
			result.setSuccess(Boolean.FALSE);
		}
		return result;
	}

}