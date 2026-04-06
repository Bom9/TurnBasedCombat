package src.actions;

public class UseItem implements Action {
  private Item itemToUse;
  
  public UseItem(Item itemToUse) {
    this.itemToUse = itemToUse;
  }

  @Override
  public void execute(Combatant user, List<Combatant> targets) {
    if (itemToUse == null) {
      System.out.println("No item selected!");
      return;
    }

    System.out.println(user.getName() + " uses " + itemToUse.getName() + "!");

    for (Combatant target : targets) {
      itemToUse.use(user, target);
    }

    if (user.getInventory().contains(itemToUse)) {
      user.getInventory().remove(itemToUse);
    }
  }

  @Override
  public String getName() {
    if (itemToUse != null) {
      return "Use Item: " + itemToUse.getName();
    }
    return "Use Item";
  }
}
