package com.example.citywarsphase2_1;

public enum Spells {
    Shield(1), Heal(1), PowerIncrease(1), HoleChanger(1), Fix(1), RoundDecrease(1),
    TakeCard(1), Weaken(1), Copy(1), Conceal(1);
    int Price;
    Spells(int Price){
        this.Price = Price;
    }
}
