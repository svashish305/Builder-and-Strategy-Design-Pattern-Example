public class Cake {
  private final double sugar;
  private final double butter;
  private final int eggs;
  private final int vanilla;
  private final double flour; //mandatory
  private final double bakingpowder; //mandatory
  private final double milk; //mandatory
  private final int cherry;

  private Cake(CakeBuilder builder) {
    this.sugar=builder.sugar;
    this.butter=builder.butter;
    this.eggs=builder.eggs;
    this.vanilla=builder.vanilla;
    this.flour=builder.flour;
    this.bakingpowder=builder.bakingpowder;
    this.milk=builder.milk;
    this.cherry=builder.cherry;
  }

  public double getSugar() {
    return sugar;
  }

  public double getButter() {
    return butter;
  }

  public int getEggs() {
    return eggs;
  }

  public int getVanilla() {
    return vanilla;
  }

  public double getFlour() {
    return flour;
  }

  public double getBakingpowder() {
    return bakingpowder;
  }

  public double getMilk() {
    return milk;
  }

  public int getCherry() {
    return cherry;
  }

  @Override
  public String toString() {
    return "Cake: "
            +"\nSugar: "+this.sugar
            +"\nButter: "+this.butter
            +"\nEggs: "+this.eggs
            +"\nVanilla: "+this.vanilla
            +"\nFlour: "+this.flour
            +"\nBakingPowder: "+this.bakingpowder
            +"\nMilk: "+this.milk
            +"\nCherry: "+this.cherry
            +"\n===================================";
  }

  public static class CakeBuilder {
    private double sugar;
    private double butter;
    private int eggs;
    private int vanilla;
    private final double flour; 
    private final double bakingpowder; 
    private final double milk; 
    private int cherry;

    public CakeBuilder(double flour, double bakingpowder, double milk) {
      this.flour=flour;
      this.bakingpowder=bakingpowder;
      this.milk=milk;
    }

    public CakeBuilder setSugar(double sugar) {
      this.sugar=sugar;
      return this;
    }

    public CakeBuilder setButter(double butter) {
      this.butter=butter;
      return this;
    }

    public CakeBuilder setEggs(int eggs) {
      this.eggs=eggs;
      return this;
    }

    public CakeBuilder setVanilla(int vanilla) {
      this.vanilla=vanilla;
      return this;
    }

    public CakeBuilder setCherry(int cherry) {
      this.cherry=cherry;
      return this;
    }

    public Cake build() {
      Cake cake = new Cake(this);
      validateCakeObject(cake);
      return cake;
    }

    private void validateCakeObject(Cake cake) {
      // add validations
      if(cake.getVanilla()>0 && cake.getSugar()==0.0) {
        try {
          throw new Exception("Can't make cake! if there is some vanilla, there must be some sugar");
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
    Cake cake1 = new Cake.CakeBuilder(2.3, 1.8, 3.0)
                 .setSugar(0.5)
                 .setButter(0.25)
                 .setEggs(1)
                 .setVanilla(2)
                 .setCherry(10)
                 .build();
    System.out.println(cake1);
    
    Cake cake2 = new Cake.CakeBuilder(10.2, 22.7, 5.5)
                .setButter(0.25)
                .setEggs(1)
                .setVanilla(2)
                .setCherry(10)
                .build();
    System.out.println(cake2);
  }
}