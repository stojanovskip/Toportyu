describe('orderCreation', function () {

    var orderTextBox;
    var orderCostBox;
    var sendButton;
    it('should not be shown on startup', function () {
        browser.get(browser.baseUrl);
        orderTextBox = element(by.model('newOrder.content'));
        orderCostBox = element(by.model('newOrder.cost'));
        sendButton = element(by.id('sendButton'));

        expect(orderTextBox.isDisplayed()).toBeFalsy();
    });

    it('should appear when a trip is selected', function () {
        var dropDown = element(by.id('selectTrip'));

        dropDown.click().then(function () {
            element.all(by.css('select[id="selectTrip"] option')).then(function (elements) {
                elements[elements.length - 1].click();
            });
        });

        expect(orderTextBox.isDisplayed()).toBeTruthy();
    });

    it('should not be able to post an order with no content', function () {
        sendButton.click();
        var ordersOfTrip = element.all(by.binding('order.content'));
        expect(ordersOfTrip.count()).toBe(0);
    });
    it('should not be able to post an order with price higher than 999999', function () {
        orderTextBox.sendKeys("testorder");
        orderCostBox.sendKeys("1000000");
        sendButton.click();
        var ordersOfTrip = element.all(by.binding('order.cost'));
        expect(ordersOfTrip.count()).toBe(0);
    });
    it('should be able to post an order', function () {
        orderTextBox.clear();
        orderCostBox.clear();
        orderTextBox.sendKeys("testorder" + Math.floor(Math.random() * (100)));
        orderCostBox.sendKeys("100");
        sendButton.click();
        var ordersOfTrip = element.all(by.binding('order.content'));
        expect(ordersOfTrip.count()).toBe(1);
    });
});
