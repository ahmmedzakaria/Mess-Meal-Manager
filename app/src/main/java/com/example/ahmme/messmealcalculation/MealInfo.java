package com.example.ahmme.messmealcalculation;

/**
 * Created by ahmme on 30-07-16.
 */
public class MealInfo {
    private int id;
    private double totalBazar;
    private double totalExtra;
    private String name;
    private double deposit;
    private double meal;
    private double totalMeal;
    private double mealCost;
    private double mealRet;
    private double RestMony;
    private int totalMassMember;

    public MealInfo(int id, String name, double deposit, double meal, double MealCost, double restMony) {
        this.id=id;
        this.name = name;
        this.deposit = deposit;
        this.meal = meal;
        this.mealCost = MealCost;
        RestMony = restMony;
    }

    public MealInfo(int id, String name, double deposit, double meal) {
        this.id = id;
        this.name = name;
        this.deposit = deposit;
        this.meal = meal;
    }
    public MealInfo(String name, double deposit, double meal) {
        this.name = name;
        this.deposit = deposit;
        this.meal = meal;
    }
    public MealInfo() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalBazar() {
        return totalBazar;
    }

    public void setTotalBazar(double totalBazar) {
        this.totalBazar = totalBazar;
    }

    public double getTotalExtra() {
        return totalExtra;
    }

    public void setTotalExtra(double totalExtra) {
        this.totalExtra = totalExtra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getMeal() {
        return meal;
    }

    public void setMeal(double meal) {
        this.meal = meal;
    }

    public double getTotalMeal() {
        return totalMeal;
    }

    public void setTotalMeal(double totalMeal) {
        this.totalMeal = totalMeal;
    }

    public double getMealCost() {
        return mealCost;
    }

    public void setMealCost(double MealCost) {
        this.mealCost = MealCost;
    }

    public double getMealRet() {
        return mealRet;
    }

    public void setMealRet(double mealRet) {
        this.mealRet = mealRet;
    }

    public double getRestMony() {
        return RestMony;
    }

    public void setRestMony(double restMony) {
        RestMony = restMony;
    }

    public int getTotalMassMember() {
        return totalMassMember;
    }

    public void setTotalMassMember(int totalMassMember) {
        this.totalMassMember = totalMassMember;
    }


}
