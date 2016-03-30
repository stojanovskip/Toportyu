package com.company;

/**
 * Created by Madi.Yessirkepov on 3/21/2016.
 */
public class Application {

    ConsoleInput consoleInput = new ConsoleInput();
    OrderStore orderStore =new OrderStore();


    public void ReadSaveData()
    {
        orderStore.addOrder(consoleInput.ReadInput());
    }
}
