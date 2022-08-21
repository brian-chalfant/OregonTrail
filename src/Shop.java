import java.util.HashMap;

public class Shop {
    public static void run(Player player){
        HashMap<ShopItem, Integer> purchases = new HashMap<>();
        int bill = 0;

        boolean purchaseCompeted = false;
        ShopItemGroup[] groups = ShopItemGroup.values;
        while(!purchaseCompeted){
            for(int i = 0; i < groups.length; i++){
                ShopItemGroup group = groups[i];
                int cost = getCost(purchases, group);
                Utils.println("display_shop_item", i + 1, group.name, displayMoney(cost));
            }
            Utils.println("clear_shop", groups.length + 1);
            Utils.println("display_shop_total", displayMoney(bill), displayMoney((player.getMoney())));
            int userInput = Keyboard.nextInt(0) - 1;
            Utils.clearScreen();

            if(userInput >= 0 && userInput < groups.length){
                ShopItemGroup group = groups[userInput];
                Utils.println(group.shopMessageID(), displayMoney(bill));
                for(int i = 0; i < group.size(); i++){
                    ShopItem item = group.get(i);
                    if(group.size() > 1){
                        Utils.println("buy_single_part", item.name.toLowerCase());
                    }

                    int item_cart_count = purchases.getOrDefault(item, 0);
                    int item_inventory_count = player.getInventory(item);
                    bill += purchaseItem(purchases, item, player.getMoney() - bill, item_cart_count + item_inventory_count);
                }
            }else if (userInput == groups.length){
                // clear shopping items
                bill = 0;
                purchases.clear();
            }else {
                player.setMoney(player.getMoney() - bill);
                player.applyPurchases(purchases);
                purchaseCompeted = true;
            }
        }
    }

    private static int getCost(HashMap<ShopItem, Integer> purchases, ShopItemGroup group){
        int cost = 0;
        for(int i = 0; i < group.size(); i++){
            ShopItem item = group.get(i);
            cost += item.price * purchases.getOrDefault(item, 0) / item.quantity;
        }
        return cost;
    }

    private static String displayMoney(int cents){
        int absCents = Math.abs(cents);
        return String.format((cents >= 0 ? "" : "-") + "$%d.%02d", absCents / 100, absCents % 100);
    }

    private static int purchaseItem(HashMap<ShopItem, Integer> purchases, ShopItem item, int balance, int current_quantity){
        // get the item amount from the user
        int user_amount = Keyboard.ensureNextInt(0);
        Utils.clearScreen();

        // max number of number items that can still be purchased - atleast zero
        int max_amount = Math.max(0, (item.max_quantity - current_quantity) / item.quantity);
        int amount = Math.min(user_amount, max_amount);
        int cost = item.price * amount;

        if(cost > balance || cost < 0){
            Utils.println("overbudget");
            return 0;
        }
        if(amount < user_amount){
            Utils.println("pass_wagon_capacity", item.max_quantity / item.quantity);
        }
        purchases.put(item, amount * item.quantity + purchases.getOrDefault(item, 0));
        return cost;
    }
}
