package com.ls.controller;

import com.ls.dao.Upload;
import com.ls.entity.Goods;
import com.ls.entity.goodsType;
import com.ls.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Goods/Manager")
public class GoodsManagerController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private Upload upload;

    @Autowired
    private HttpSession session;

    int pageNum = 5;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "typeName", required = false) String typeName, Model model) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("error", "您没此操作的权限,请先<a href=\"/User/Login\">登录</a>!");
            return "error/error";
        }
        //session.invalidate();
        //session.removeAttribute("typeName");
        //只消除typename
        if (typeName != null) {
            session.setAttribute("typeName", typeName);
        }
        return "redirect:/Goods/Manager/pageList?showNum=1";
    }

    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    public String pageList(Model model,
                           @RequestParam(value = "showNum", required = false) Integer showNum) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("error", "您没此操作的权限,请先<a href=\"/User/Login\">登录</a>!");
            return "error/error";
        }
        List<Goods> list;
        if (session.getAttribute("typeName") == null) {
            list = goodsService.getPageList(showNum, pageNum);
        } else {
            list = goodsService.getPageListByGoodsType(showNum, pageNum, session.getAttribute("typeName").toString());
        }
        //向上取整得到总页数
        if (session.getAttribute("typeName") == null) {
            model.addAttribute("pages", (int) Math.ceil(Double.parseDouble(goodsService.getGoodsTotalNum().toString()) / pageNum));
        } else {
            model.addAttribute("pages", (int) Math.ceil(Double.parseDouble(goodsService.getGoodsTotalNumByGoodsType(session.getAttribute("typeName").toString()).toString()) / pageNum));
        }

        model.addAttribute("showNum", showNum);

        model.addAttribute("list", list);
        model.addAttribute("goodsTypeList", goodsService.queryGoodsType());
        return "goods/manager/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("error", "您没此操作的权限,请先<a href=\"/User/Login\">登录</a>!");
            return "error/error";
        }
        model.addAttribute("goodsTypeList", goodsService.queryGoodsType());
        Map<String, Long> goodsTypeMap = new HashMap<String, Long>();
        for (goodsType goodsType : goodsService.queryGoodsType()) {
            goodsTypeMap.put(goodsType.getTypeName(), goodsType.getId());
        }
        session.setAttribute("goodsTypeMap", goodsTypeMap);
        return "goods/manager/addGoods";
    }

    @RequestMapping(value = "/addGoods", method = RequestMethod.POST)
    public String addGoods(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "type", required = false) String type,
                           @RequestParam(value = "price", required = false) Double price,
                           @RequestParam(value = "number", required = false) Integer number,
                           @RequestParam(value = "picture", required = false) MultipartFile picture,
                           @RequestParam(value = "detail", required = false) String detail,
                           Model model)
            throws IOException {
        if (session.getAttribute("user") == null) {
            model.addAttribute("error", "您没此操作的权限,请先<a href=\"/User/Login\">登录</a>!");
            return "error/error";
        }
        if (name == null || price == null || number == null || picture.getSize() == 0 || detail.length() == 0 || type.length() == 0) {
            model.addAttribute("error", "请完善信息!");
            return "error/error";
        }
        if (goodsService.insertRepeat(name).size() > 0) {
            model.addAttribute("error", "该商品已被添加!");
            return "error/error";
        }
        //解决名字中文乱码
        name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
        detail = new String(detail.getBytes("ISO-8859-1"), "UTF-8");
        type = new String(type.getBytes("ISO-8859-1"), "UTF-8");

        Map<String, Long> goodsTypeMap = (Map<String, Long>) session.getAttribute("goodsTypeMap");

        Goods goods = new Goods(name, goodsTypeMap.get(type), price, number, upload.uploadGoods(picture), detail);
        if (goodsService.insertGoods(goods) <= 0) {
            model.addAttribute("error", "增加商品失败!");
            return "error/error";
        }
        return "redirect:/Goods/Manager/list";
    }

    @RequestMapping(value = "/deleteOne", method = RequestMethod.GET)
    public String deleteOne(@RequestParam(value = "md5Id", required = false) String md5Id, Model model) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("error", "您没此操作的权限,请先<a href=\"/User/Login\">登录</a>!");
            return "error/error";
        }
        if (md5Id == null) {
            model.addAttribute("error", "找不到此商品!");
            return "error/error";
        }
        if (goodsService.deleteGoodsById(md5Id) < 1) {
            model.addAttribute("error", "删除商品失败!");
            return "error/error";
        }
        return "redirect:/Goods/Manager/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@RequestParam(value = "md5Id", required = false) String md5Id, Model model) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("error", "您没此操作的权限,请先<a href=\"/User/Login\">登录</a>!");
            return "error/error";
        }
        if (md5Id == null) {
            model.addAttribute("error", "找不到此商品!");
            return "error/error";
        }
        Goods goods = goodsService.queryGoodsDetail(md5Id);
        if (goods == null) {
            model.addAttribute("error", "找不到此商品!");
            return "error/error";
        }
        model.addAttribute("goods", goods);
        model.addAttribute("goodsTypeList", goodsService.queryGoodsType());
        return "goods/manager/updateGoodsDetail";
    }

    @RequestMapping(value = "/updateGoodsDetail", method = RequestMethod.POST)
    public String updateGoodsDetail(@RequestParam(value = "Id", required = false) Long Id,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "type", required = false) String type,
                                    @RequestParam(value = "price", required = false) Double price,
                                    @RequestParam(value = "number", required = false) Integer number,
                                    @RequestParam(value = "picture", required = false) MultipartFile picture,
                                    @RequestParam(value = "detail", required = false) String detail,
                                    Model model)
            throws IOException {
        if (session.getAttribute("user") == null) {
            model.addAttribute("error", "您没此操作的权限,请先<a href=\"/User/Login\">登录</a>!");
            return "error/error";
        }
        if (name == null || price == null || number == null || detail.length() == 0 || type.length() == 0) {
            model.addAttribute("error", "请完善信息!");
            return "error/error";
        }
        if (goodsService.insertRepeat(name).size() > 0) {
            model.addAttribute("error", "该商品已被添加!");
            return "error/error";
        }
        //解决名字中文乱码
        name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
        detail = new String(detail.getBytes("ISO-8859-1"), "UTF-8");
        type = new String(type.getBytes("ISO-8859-1"), "UTF-8");

        Map<String, Long> goodsTypeMap = (Map<String, Long>) session.getAttribute("goodsTypeMap");

        if (picture.getSize() == 0) {
            Goods goods = new Goods(Id, name, goodsTypeMap.get(type), price, number, detail);
            if (goodsService.updateGoodsDetailWithoutImg(goods) < 1) {
                model.addAttribute("error", "更新商品失败!");
                return "error/error";
            }
        } else {
            Goods goods = new Goods(Id, name, goodsTypeMap.get(type), price, number, upload.uploadGoods(picture), detail);
            if (goodsService.updateGoodsDetail(goods) < 1) {
                model.addAttribute("error", "更新商品失败!");
                return "error/error";
            }
        }
        return "redirect:/Goods/Manager/list";
    }
}




















