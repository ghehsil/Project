package com.ls.controller;

import com.ls.dto.paymentDetail;
import com.ls.dto.shoppingCartDetail;
import com.ls.entity.Goods;
import com.ls.entity.User;
import com.ls.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/Goods/Customer")
public class GoodsCustomerController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private HttpSession session;

    int pageNum = 5;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "typeName", required = false) String typeName) {
        //session.removeAttribute("typeName");
        if (typeName != null) {
            session.setAttribute("typeName", typeName);
        }
        return "redirect:/Goods/Customer/pageList?showNum=1";
    }

    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    public String pageList(Model model,
                           @RequestParam(value = "showNum", required = false) Integer showNum) {
        List<Goods> list;
        if (session.getAttribute("typeName") == null) {
            list = goodsService.getPageList(showNum, pageNum);
        } else {
            list = goodsService.getPageListByGoodsType(showNum, pageNum, session.getAttribute("typeName").toString());
        }
        //向上取整得到总页数 分两类
        if (session.getAttribute("typeName") == null) {
            model.addAttribute("pages", (int) Math.ceil(Double.parseDouble(goodsService.getGoodsTotalNum().toString()) / pageNum));
        } else {
            model.addAttribute("pages", (int) Math.ceil(Double.parseDouble(goodsService.getGoodsTotalNumByGoodsType(session.getAttribute("typeName").toString()).toString()) / pageNum));
        }

        model.addAttribute("showNum", showNum);

        model.addAttribute("list", list);
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("goodsTypeList", goodsService.queryGoodsType());
        return "goods/customer/list";
    }

    @RequestMapping(value = "/myShoppingCart", method = RequestMethod.GET)
    public String showShoppingCart(@RequestParam(value = "showNum", required = false) Integer showNum,
                                   Model model) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("error", "请先<a href=\"/User/Login\">登录</a>,若没注册,请先<a href=\"/User/Register\">注册</a>!");
            return "error/error";
        }
        User user = (User) session.getAttribute("user");

        if (showNum == null) {
            showNum = 1;
        }
        List<shoppingCartDetail> shoppingCartList = goodsService.showShoppingCart(user.getId(), showNum, pageNum);
        model.addAttribute("pages", (int) Math.ceil(Double.parseDouble(goodsService.getShoppingCartTotalNum(user.getId()).toString()) / pageNum));
        model.addAttribute("showNum", showNum);
        model.addAttribute("shoppingCart", shoppingCartList);
        return "goods/customer/shoppingCart";
    }

    @RequestMapping(value = "/addToShoppingCart", method = RequestMethod.GET)
    public String addToShoppingCart(@RequestParam(value = "md5Id", required = false) String md5Id,
                                    Model model) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("error", "请先<a href=\"/User/Login\">登录</a>,若没注册,请先<a href=\"/User/Register\">注册</a>!");
            return "error/error";
        }
        //md5加密,防止恶意篡改
        Long Id = goodsService.getMd5Id(md5Id);
        //得到顾客Id
        User user = (User) session.getAttribute("user");
        if (goodsService.addShoppingCart(user.getId(), Id) < 1) {
            model.addAttribute("error", "加入购物车失败!");
            return "error/error";
        }
        return "redirect:/Goods/Customer/myShoppingCart";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(@RequestParam(value = "md5Id", required = false) String md5Id,
                         Model model) {
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
        return "goods/customer/detail";
    }

    @RequestMapping(value = "/deleteOne", method = RequestMethod.GET)
    public String deleteOne(@RequestParam(value = "Id", required = false) Long Id,
                            Model model) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("error", "请先<a href=\"/User/Login\">登录</a>,若没注册,请先<a href=\"/User/Register\">注册</a>!");
            return "error/error";
        }
        if (Id == null) {
            model.addAttribute("error", "找不到此商品!");
            return "error/error";
        }
        User user = (User) session.getAttribute("user");
        if (goodsService.deleteOne(user.getId(), Id) < 1) {
            model.addAttribute("error", "删除失败!");
            return "error/error";
        }
        return "redirect:/Goods/Customer/myShoppingCart";
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
    public String deleteAll(@RequestParam(value = "Id", required = false) Long Id,
                            Model model) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("error", "请先<a href=\"/User/Login\">登录</a>,若没注册,请先<a href=\"/User/Register\">注册</a>!");
            return "error/error";
        }
        if (Id == null) {
            model.addAttribute("error", "找不到此商品!");
            return "error/error";
        }
        User user = (User) session.getAttribute("user");
        if (goodsService.deleteAll(user.getId(), Id) < 1) {
            model.addAttribute("error", "删除失败!");
            return "error/error";
        }
        return "redirect:/Goods/Customer/myShoppingCart";
    }

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public String pay(@RequestParam(value = "Id", required = false) Long Id,
                      Model model) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("error", "请先<a href=\"/User/Login\">登录</a>,若没注册,请先<a href=\"/User/Register\">注册</a>!");
            return "error/error";
        }
        User user = (User) session.getAttribute("user");
        if (goodsService.pay(user.getId(), Id) < 1) {
            model.addAttribute("error", "支付失败!");
            return "error/error";
        }
        return "redirect:/Goods/Customer/myShoppingCart";
    }

    @RequestMapping(value = "/myPayment", method = RequestMethod.GET)
    public String showPayment(@RequestParam(value = "showNum", required = false) Integer showNum,
                              Model model) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("error", "请先<a href=\"/User/Login\">登录</a>,若没注册,请先<a href=\"/User/Register\">注册</a>!");
            return "error/error";
        }
        User user = (User) session.getAttribute("user");
        if (showNum == null) {
            showNum = 1;
        }
        List<paymentDetail> paymentList = goodsService.showPayment(user.getId(), showNum, pageNum);
        model.addAttribute("pages", (int) Math.ceil(Double.parseDouble(goodsService.getShoppingCartTotalNum(user.getId()).toString()) / pageNum));
        model.addAttribute("showNum", showNum);
        model.addAttribute("payment", paymentList);
        return "goods/customer/payment";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam(value = "name", required = false) String name,
                         Model model) throws IOException {
        if (name == null) {
            model.addAttribute("error", "找不到此商品!");
            return "error/error";
        }
        name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
        List<Goods> list = goodsService.insertRepeat(name);
        if (list.size() == 0) {
            model.addAttribute("error", "找不到此商品!");
            return "error/error";
        }
        Goods goods = list.get(0);
        model.addAttribute("goods", goods);
        return "goods/customer/detail";
    }
}
