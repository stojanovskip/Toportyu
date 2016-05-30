var orderName = 'testOrder' + Math.floor(Math.random()*(100));

describe('order creation:', function() {
    browser.get(browser.baseUrl);
    var contentTextBox;
    var costTextBox;
    var sendButton;
    it('should not show orders before trip selection', function() {
        contentTextBox = element(by.model('newOrder.content'));
        costTextBox = element(by.model('newOrder.cost'));
        sendButton = element(by.id('sendButton'));
        expect(contentTextBox.isDisplayed()).toBeFalsy();
    });
    it('should show orders after trip selection', function() {
        var dropDown = element(by.id('selectTrip'));
        dropDown.click().then(function() {
            element.all(by.css('select[id="selectTrip"] option')).then(function(elements) {
                elements[elements.length - 1].click();
            });
        });
        expect(contentTextBox.isDisplayed()).toBeTruthy();
    });
    it('should not post orders with no content', function() {
        costTextBox.sendKeys(100);
        sendButton.click();
    var ordersOfTrip = element.all(by.binding('order.cost'));
    expect(ordersOfTrip.count()).toBe(0);
});
    it('should not post orders with cost higher than 999999', function() {
        costTextBox.clear();
       contentTextBox.sendKeys(orderName.toString());
       costTextBox.sendKeys(100000000);
       sendButton.click();
        var ordersOfTrip = element.all(by.binding('order.cost'));
        expect(ordersOfTrip.count()).toBe(0);
    });
    it('should post orders', function() {
        contentTextBox.clear();
        costTextBox.clear();
        contentTextBox.sendKeys(orderName.toString());
        costTextBox.sendKeys(100);
        sendButton.click();
        var ordersOfTrip = element.all(by.binding('order.cost'));
        expect(ordersOfTrip.count()).toBe(1);
    });
});