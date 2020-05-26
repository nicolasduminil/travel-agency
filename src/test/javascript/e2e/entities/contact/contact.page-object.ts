import { element, by, ElementFinder } from 'protractor';

export class ContactComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-contact div table .btn-danger'));
  title = element.all(by.css('jhi-contact div h2#page-heading span')).first();
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

export class ContactUpdatePage {
  pageTitle = element(by.id('jhi-contact-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  contactNameInput = element(by.id('field_contactName'));
  contactFirstNameInput = element(by.id('field_contactFirstName'));
  contactLastNameInput = element(by.id('field_contactLastName'));
  contactEmailAddressInput = element(by.id('field_contactEmailAddress'));
  contactWebSiteInput = element(by.id('field_contactWebSite'));
  contactSalutationSelect = element(by.id('field_contactSalutation'));
  contactJobTitleInput = element(by.id('field_contactJobTitle'));
  contactPhoneNumberInput = element(by.id('field_contactPhoneNumber'));
  contactFaxNumberInput = element(by.id('field_contactFaxNumber'));

  addressSelect = element(by.id('field_address'));
  activitySelect = element(by.id('field_activity'));
  customerSelect = element(by.id('field_customer'));
  dealSelect = element(by.id('field_deal'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setContactNameInput(contactName: string): Promise<void> {
    await this.contactNameInput.sendKeys(contactName);
  }

  async getContactNameInput(): Promise<string> {
    return await this.contactNameInput.getAttribute('value');
  }

  async setContactFirstNameInput(contactFirstName: string): Promise<void> {
    await this.contactFirstNameInput.sendKeys(contactFirstName);
  }

  async getContactFirstNameInput(): Promise<string> {
    return await this.contactFirstNameInput.getAttribute('value');
  }

  async setContactLastNameInput(contactLastName: string): Promise<void> {
    await this.contactLastNameInput.sendKeys(contactLastName);
  }

  async getContactLastNameInput(): Promise<string> {
    return await this.contactLastNameInput.getAttribute('value');
  }

  async setContactEmailAddressInput(contactEmailAddress: string): Promise<void> {
    await this.contactEmailAddressInput.sendKeys(contactEmailAddress);
  }

  async getContactEmailAddressInput(): Promise<string> {
    return await this.contactEmailAddressInput.getAttribute('value');
  }

  async setContactWebSiteInput(contactWebSite: string): Promise<void> {
    await this.contactWebSiteInput.sendKeys(contactWebSite);
  }

  async getContactWebSiteInput(): Promise<string> {
    return await this.contactWebSiteInput.getAttribute('value');
  }

  async setContactSalutationSelect(contactSalutation: string): Promise<void> {
    await this.contactSalutationSelect.sendKeys(contactSalutation);
  }

  async getContactSalutationSelect(): Promise<string> {
    return await this.contactSalutationSelect.element(by.css('option:checked')).getText();
  }

  async contactSalutationSelectLastOption(): Promise<void> {
    await this.contactSalutationSelect.all(by.tagName('option')).last().click();
  }

  async setContactJobTitleInput(contactJobTitle: string): Promise<void> {
    await this.contactJobTitleInput.sendKeys(contactJobTitle);
  }

  async getContactJobTitleInput(): Promise<string> {
    return await this.contactJobTitleInput.getAttribute('value');
  }

  async setContactPhoneNumberInput(contactPhoneNumber: string): Promise<void> {
    await this.contactPhoneNumberInput.sendKeys(contactPhoneNumber);
  }

  async getContactPhoneNumberInput(): Promise<string> {
    return await this.contactPhoneNumberInput.getAttribute('value');
  }

  async setContactFaxNumberInput(contactFaxNumber: string): Promise<void> {
    await this.contactFaxNumberInput.sendKeys(contactFaxNumber);
  }

  async getContactFaxNumberInput(): Promise<string> {
    return await this.contactFaxNumberInput.getAttribute('value');
  }

  async addressSelectLastOption(): Promise<void> {
    await this.addressSelect.all(by.tagName('option')).last().click();
  }

  async addressSelectOption(option: string): Promise<void> {
    await this.addressSelect.sendKeys(option);
  }

  getAddressSelect(): ElementFinder {
    return this.addressSelect;
  }

  async getAddressSelectedOption(): Promise<string> {
    return await this.addressSelect.element(by.css('option:checked')).getText();
  }

  async activitySelectLastOption(): Promise<void> {
    await this.activitySelect.all(by.tagName('option')).last().click();
  }

  async activitySelectOption(option: string): Promise<void> {
    await this.activitySelect.sendKeys(option);
  }

  getActivitySelect(): ElementFinder {
    return this.activitySelect;
  }

  async getActivitySelectedOption(): Promise<string> {
    return await this.activitySelect.element(by.css('option:checked')).getText();
  }

  async customerSelectLastOption(): Promise<void> {
    await this.customerSelect.all(by.tagName('option')).last().click();
  }

  async customerSelectOption(option: string): Promise<void> {
    await this.customerSelect.sendKeys(option);
  }

  getCustomerSelect(): ElementFinder {
    return this.customerSelect;
  }

  async getCustomerSelectedOption(): Promise<string> {
    return await this.customerSelect.element(by.css('option:checked')).getText();
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

export class ContactDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-contact-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-contact'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
