package org.springframework.samples.casuallab.casual;

// 1️⃣ Define the base interface for discount rules
interface DiscountRule {
    // Default implementation: not applicable
    default boolean isApplicable(CartItem item) {
        return false;
    }

    // Apply discount to the item
    double apply(CartItem item);
}

// 2️⃣ Cart item class
class CartItem {
    String name;
    double price;
    boolean isVIP;

    public CartItem(String name, double price, boolean isVIP) {
        this.name = name;
        this.price = price;
        this.isVIP = isVIP;
    }
}

// 3️⃣ Implement a VIP discount rule
class VIPDiscountRule implements DiscountRule {
    // Only applicable for VIP items
    @Override
    public boolean isApplicable(CartItem item) {
        return item.isVIP;
    }

    // 20% discount
    @Override
    public double apply(CartItem item) {
        return item.price * 0.8;
    }
}

// 4️⃣ Implement a Holiday discount rule
class HolidayDiscountRule implements DiscountRule {
    @Override
    public boolean isApplicable(CartItem item) {
        // Apply to all items
        return true;
    }

    // 10% discount
    @Override
    public double apply(CartItem item) {
        return item.price * 0.9;
    }
}

// 5️⃣ System that handles discount rules
class DiscountHandler {
    DiscountRule[] rules;

    public DiscountHandler(DiscountRule... rules) {
        this.rules = rules;
    }

    public double getFinalPrice(CartItem item) {
        double price = item.price;

        for (DiscountRule rule : rules) {
            if (rule.isApplicable(item)) {
                price = rule.apply(item); // Apply first matching rule
                break; // Stop after applying a rule
            }
        }

        return price;
    }
}

// 6️⃣ Test the discount system
public class CartItemDemo {
    public static void main(String[] args) {
        CartItem normalItem = new CartItem("Laptop", 1000, false);
        CartItem vipItem = new CartItem("Smartphone", 800, true);

        DiscountHandler handler = new DiscountHandler(new VIPDiscountRule(), new HolidayDiscountRule());

        System.out.println("Normal item final price: $" + handler.getFinalPrice(normalItem));
        System.out.println("VIP item final price: $" + handler.getFinalPrice(vipItem));
    }
}

