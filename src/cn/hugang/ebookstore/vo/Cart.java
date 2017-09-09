package cn.hugang.ebookstore.vo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/5.
 */
public class Cart {
    private double total;
    Map<String, CartItem> map = new HashMap<>();//用于存放购物项

    public double getTotal() {
        return total;
    }

    public Collection<CartItem> getCartItems(){
        return map.values();
    }

    public void addCart(CartItem item){
        total += item.getSubTotal();
        String bid = item.getBook().getBid();
        if (map.containsKey(bid)){
            CartItem oldItem = map.get(bid);
            System.out.println(item.getCount());
            System.out.println(oldItem.getCount());
            item.setCount(item.getCount() + oldItem.getCount());
            System.out.println(item.getCount());
        }
        map.put(bid, item);
    }

    public void removeCart(String bid){
        CartItem item = map.remove(bid);
        total = total - item.getSubTotal();
    }

    public void clearCart(){
        map.clear();
        total = 0;
    }
}
