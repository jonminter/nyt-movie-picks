import { ElementFinder } from 'protractor';
import * as moment from 'moment';

export class E2EUtils {
  static expectNonEmptyString(elem: ElementFinder) {
    elem.getText().then(text => {
      expect(text.length > 0).toBeTruthy();
    });
  }

  static expectValidDate(elem: ElementFinder, dateFormat: string) {
    elem.getText().then(text => {
      expect(moment(text, dateFormat).isValid()).toBeTruthy();
    });
  }
}
