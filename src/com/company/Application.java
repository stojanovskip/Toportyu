package com.company;

/**
 * Created by Madi.Yessirkepov on 3/21/2016.
 */
public class Application {

    OrderParser orderParser = new OrderParser();
    ConsoleInput consoleInput = new ConsoleInput();
    OrderStore orderStore = new OrderStore();


    public void ReadSaveData() {
        orderStore.addOrder(orderParser.parseOrder(consoleInput.ReadInput()));
    }
}
