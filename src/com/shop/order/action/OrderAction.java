package com.shop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.cart.vo.Cart;
import com.shop.cart.vo.CartItem;
import com.shop.order.service.OrderService;
import com.shop.order.vo.Order;
import com.shop.order.vo.OrderItem;
import com.shop.user.vo.User;
import com.shop.utils.PageBean;
import com.shop.utils.PaymentUtil;

/*
 * ��������action
 */
@Transactional
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	private Order order = new Order();
	//ע��orderservice
	private OrderService orderService;
	
	private Integer page;
	
	private String pd_FrpId;

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	// ���ո���ɹ���Ĳ���:
	private String r3_Amt;
	private String r6_Order;
	
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Order getModel(){
		return order;
	}
	public String saveOrder(){
		//�������ݵ����ݿ�
		//������������ʾ��ҳ��
		order.setOrdertime(new Date());
		order.setState(1);//1:δ���� 2���Ѿ�����û�з��� 3���Ѿ�����û��ȷ���ջ� 4���������
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null){
			this.addActionError("����û�й���");
			return "msg";
		}
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser==null){
			this.addActionError("����δ��¼");
			return "msg";
		}
		order.setUser(existUser);
		order.setTotal(cart.getTotal());
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
			
		}
		//���ö��������û�

		orderService.save(order);
		//ͨ��ֵջ��ʾ����,����Ϊorder��ģ����������,������ջ��
		cart.clearCart();
		return "saveOrder";
	}
	public String findByUid(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(),page);
		
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}
	public String findByOid(){
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
	public String payOrder() throws IOException {
		// 1.�޸�����:
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		// �޸Ķ���
		orderService.update(currOrder);
		// 2.��ɸ���:
		// ������Ҫ�Ĳ���:
		String p0_Cmd = "Buy"; // ҵ������:
		String p1_MerId = "10001126856";// �̻����:
		String p2_Order = order.getOid().toString();// �������:
		String p3_Amt = "0.01"; // ������:
		String p4_Cur = "CNY"; // ���ױ���:
		String p5_Pid = ""; // ��Ʒ����:
		String p6_Pcat = ""; // ��Ʒ����:
		String p7_Pdesc = ""; // ��Ʒ����:
		String p8_Url = "http://192.168.36.69:8080/shop/order_callBack.action"; // �̻�����֧���ɹ����ݵĵ�ַ:
		String p9_SAF = ""; // �ͻ���ַ:
		String pa_MP = ""; // �̻���չ��Ϣ:
		String pd_FrpId = this.pd_FrpId;// ֧��ͨ������:
		String pr_NeedResponse = "1"; // Ӧ�����:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // ��Կ
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// ���ױ���������:
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		// �ض���:���ױ�����:
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}

	// ����ɹ�����ת������·��:
	public String callBack(){
		// �޸Ķ�����״̬:
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		// �޸Ķ���״̬Ϊ2:�Ѿ�����:
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("֧���ɹ�!�������Ϊ: "+r6_Order +" ������Ϊ: "+r3_Amt);
		return "msg";
	}
	
	// �޸Ķ�����״̬:
	public String updateState(){
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
	

}
