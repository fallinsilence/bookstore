package cn.hugang.ebookstore.servlet;

import cn.hugang.ebookstore.service.OrderService;
import cn.hugang.ebookstore.utils.PaymentUtil;
import cn.hugang.ebookstore.utils.UUIDUtils;
import cn.hugang.ebookstore.vo.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */
@WebServlet(urlPatterns = "/order")
public class OrderServlet extends BaseServlet{
    public String createOrder( HttpServletRequest request,HttpServletResponse response){
        //获取当前用户的购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //获取当前用户
        User user = (User) request.getSession().getAttribute("existUser");
        //为订单设置属性
        Order order = new Order();
        order.setOid(UUIDUtils.getUUID());
        order.setAddress(null);
        order.setOrderTime(null);
        //状态:1为订单已提交未付款，2为已付款未发货，3为已发货未收货，4为订单完成
        order.setState(1);
        order.setTotal(cart.getTotal());
        order.setUser(user);
        //将订单项加入订单
        for(CartItem cartItem : cart.getCartItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setCount(cartItem.getCount());
            orderItem.setSubtotal(cartItem.getSubTotal());
            orderItem.setItemid(UUIDUtils.getUUID());
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }

        OrderService service = new OrderService();
        service.save(order);

        order = service.findByOid(order.getOid());

        request.setAttribute("order", order);

        cart.clearCart();

        return  "/jsps/order/desc.jsp";
    }

    public String findById(HttpServletRequest request, HttpServletResponse response){
        String uid = request.getParameter("uid");
        OrderService service = new OrderService();
        List<Order> list = service.findById(uid);
        request.setAttribute("list", list);
        return "/jsps/order/list.jsp";
    }

    public String findByOid(HttpServletRequest request, HttpServletResponse response){
        String oid = request.getParameter("oid");
        OrderService service = new OrderService();
        Order order = service.findByOid(oid);
        request.setAttribute("order", order);
        return "/jsps/order/desc.jsp";
    }

    public String payOrder(HttpServletRequest request, HttpServletResponse response){
        String oid = request.getParameter("oid");     //订单编号
        String address = request.getParameter("address");   //地址
        String pd_FrpId = request.getParameter("pd_FrpId"); //支付通道编号

        //准备参数
        String p0_Cmd = "Buy";  //业务类型
        String p1_MerId = "10001126856";   //商户编号
        String p2_Order = oid;  //订单号
        String p3_Amt = "0.01";  //支付金额
        String p4_Cur = "CNY";   //支付币种
        String p5_Pid = "Book";   //商品名称
        String p6_Pcat = "boook";      //商品种类
        String p7_Pdesc = "good book"; //商品描述
        String p8_Url = "";             //付款成功后跳转的页面
        String p9_SAF = "";             //送货地址
        String pa_MP = "CNM";           //商户扩展信息
        String pr_NeedResponse = "1";   //应答机制
        String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
        String hmac= PaymentUtil.buildHmac(p0_Cmd, p1_MerId,p2_Order,p3_Amt,p4_Cur,p5_Pid ,p6_Pcat ,
                                           p7_Pdesc ,p8_Url ,p9_SAF ,pa_MP ,pd_FrpId ,pr_NeedResponse ,keyValue);

        //要向易宝提交参数
        StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
        sb.append("p0_Cmd=").append(p0_Cmd).append("&p1_MerId=").append(p1_MerId).append("&p2_Order=").append(p2_Order).append("&p3_Amt=").
                append(p3_Amt).append("&p4_Cur=").append(p4_Cur).append("&p5_Pid=").append(p5_Pid).append("&p6_Pcat=").append(p6_Pcat).
                append("&p7_Pdesc=").append(p7_Pdesc).append("&p8_Url=").append(p8_Url).append("&p9_SAF=").append(p9_SAF).append("&pa_MP=").
                append(pa_MP).append("&pd_FrpId=").append(pd_FrpId).append("&pr_NeedResponse=").append(pr_NeedResponse).append("&hmac=").append(hmac);

        try {
            response.sendRedirect(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
