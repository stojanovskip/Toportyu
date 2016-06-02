
var tripname = "testTrip" + Math.floor(Math.random() * (100));

describe('trip creation', function () {
    browser.get(browser.baseUrl);

    it('should not show trip creation before add is clicked', function () {
        var tripTextBox = element(by.model('newTrip.name'));
        expect(tripTextBox.isDisplayed()).toBeFalsy();
    });

    it('should show trip creation when add is clicked', function () {
        element(by.id('addButton')).click();
        var tripTextBox = element(by.model('newTrip.name'));
        expect(tripTextBox.isDisplayed()).toBeTruthy();
    });

    it('should be able to add a new trip', function () {
        var initLength;
        element.all(by.css('select[id="selectTrip"] option')).then(function (elements) {
            initLength = elements.length;
        });

        element(by.model('newTrip.name')).sendKeys(tripname.toString());
        element(by.id('okButton')).click();
        element.all(by.css('select[id="selectTrip"] option')).then(function (elements) {
            expect(elements.length > initLength).toBeTruthy();
        });
    });
});

