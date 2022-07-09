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
            Utils.println("display_shop_total", displayMoney(bill), displayMoney((player.getMoney())));
            int userInput = Keyboard.nextInt(0) - 1;

            if(userInput >= 0 && userInput < groups.length){
                ShopItemGroup group = groups[userInput];
                Utils.println(group.shopMessageID(), displayMoney(bill));
                for(int i = 0; i < group.size(); i++){
                    ShopItem item = group.get(i);
                    if(group.size() > 1){
                        Utils.println("buy_single_part", item.name.toLowerCase());
                    }
                    bill += purchaseItem(purchases, item);
                }
            }else if(bill > player.getMoney()){
                Utils.println("overbudget");
            } else {
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

    private static int purchaseItem(HashMap<ShopItem, Integer> purchases, ShopItem item){
        int amount = Keyboard.nextInt(0);
        purchases.put(item, amount * item.quantity + purchases.getOrDefault(item, 0));
        return item.price * amount;
    }
}
