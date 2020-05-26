import { element, by, ElementFinder } from 'protractor';

export class AccomodationComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-accomodation div table .btn-danger'));
  title = element.all(by.css('jhi-accomodation div h2#page-heading span')).first();
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

export class AccomodationUpdatePage {
  pageTitle = element(by.id('jhi-accomodation-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  accomodationNameInput = element(by.id('field_accomodationName'));
  accomodationTypeSelect = element(by.id('field_accomodationType'));
  accomodationClassSelect = element(by.id('field_accomodationClass'));

  locationSelect = element(by.id('field_location'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setAccomodationNameInput(accomodationName: string): Promise<void> {
    await this.accomodationNameInput.sendKeys(accomodationName);
  }

  async getAccomodationNameInput(): Promise<string> {
    return await this.accomodationNameInput.getAttribute('value');
  }

  async setAccomodationTypeSelect(accomodationType: string): Promise<void> {
    await this.accomodationTypeSelect.sendKeys(accomodationType);
  }

  async getAccomodationTypeSelect(): Promise<string> {
    return await this.accomodationTypeSelect.element(by.css('option:checked')).getText();
  }

  async accomodationTypeSelectLastOption(): Promise<void> {
    await this.accomodationTypeSelect.all(by.tagName('option')).last().click();
  }

  async setAccomodationClassSelect(accomodationClass: string): Promise<void> {
    await this.accomodationClassSelect.sendKeys(accomodationClass);
  }

  async getAccomodationClassSelect(): Promise<string> {
    return await this.accomodationClassSelect.element(by.css('option:checked')).getText();
  }

  async accomodationClassSelectLastOption(): Promise<void> {
    await this.accomodationClassSelect.all(by.tagName('option')).last().click();
  }

  async locationSelectLastOption(): Promise<void> {
    await this.locationSelect.all(by.tagName('option')).last().click();
  }

  async locationSelectOption(option: string): Promise<void> {
    await this.locationSelect.sendKeys(option);
  }

  getLocationSelect(): ElementFinder {
    return this.locationSelect;
  }

  async getLocationSelectedOption(): Promise<string> {
    return await this.locationSelect.element(by.css('option:checked')).getText();
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

export class AccomodationDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-accomodation-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-accomodation'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
