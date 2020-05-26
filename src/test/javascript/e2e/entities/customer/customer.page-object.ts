import { element, by, ElementFinder } from 'protractor';

export class CustomerComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-customer div table .btn-danger'));
  title = element.all(by.css('jhi-customer div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class CustomerUpdatePage {
  pageTitle = element(by.id('jhi-customer-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  customerNameInput = element(by.id('field_customerName'));
  customerGenderSelect = element(by.id('field_customerGender'));
  customerBirthDateInput = element(by.id('field_customerBirthDate'));

  dealSelect = element(by.id('field_deal'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setCustomerNameInput(customerName: string): Promise<void> {
    await this.customerNameInput.sendKeys(customerName);
  }

  async getCustomerNameInput(): Promise<string> {
    return await this.customerNameInput.getAttribute('value');
  }

  async setCustomerGenderSelect(customerGender: string): Promise<void> {
    await this.customerGenderSelect.sendKeys(customerGender);
  }

  async getCustomerGenderSelect(): Promise<string> {
    return await this.customerGenderSelect.element(by.css('option:checked')).getText();
  }

  async customerGenderSelectLastOption(): Promise<void> {
    await this.customerGenderSelect.all(by.tagName('option')).last().click();
  }

  async setCustomerBirthDateInput(customerBirthDate: string): Promise<void> {
    await this.customerBirthDateInput.sendKeys(customerBirthDate);
  }

  async getCustomerBirthDateInput(): Promise<string> {
    return await this.customerBirthDateInput.getAttribute('value');
  }

  async dealSelectLastOption(): Promise<void> {
    await this.dealSelect.all(by.tagName('option')).last().click();
  }

  async dealSelectOption(option: string): Promise<void> {
    await this.dealSelect.sendKeys(option);
  }

  getDealSelect(): ElementFinder {
    return this.dealSelect;
  }

  async getDealSelectedOption(): Promise<string> {
    return await this.dealSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class CustomerDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-customer-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-customer'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
