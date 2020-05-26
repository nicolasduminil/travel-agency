import { element, by, ElementFinder } from 'protractor';

export class ServiceComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-service div table .btn-danger'));
  title = element.all(by.css('jhi-service div h2#page-heading span')).first();
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

export class ServiceUpdatePage {
  pageTitle = element(by.id('jhi-service-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  serviceDescriptionInput = element(by.id('field_serviceDescription'));
  serviceStartDateInput = element(by.id('field_serviceStartDate'));
  serviceEndDateInput = element(by.id('field_serviceEndDate'));
  servicePriceInput = element(by.id('field_servicePrice'));

  dealsSelect = element(by.id('field_deals'));
  packagesSelect = element(by.id('field_packages'));
  accomodationsSelect = element(by.id('field_accomodations'));
  activitiesSelect = element(by.id('field_activities'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setServiceDescriptionInput(serviceDescription: string): Promise<void> {
    await this.serviceDescriptionInput.sendKeys(serviceDescription);
  }

  async getServiceDescriptionInput(): Promise<string> {
    return await this.serviceDescriptionInput.getAttribute('value');
  }

  async setServiceStartDateInput(serviceStartDate: string): Promise<void> {
    await this.serviceStartDateInput.sendKeys(serviceStartDate);
  }

  async getServiceStartDateInput(): Promise<string> {
    return await this.serviceStartDateInput.getAttribute('value');
  }

  async setServiceEndDateInput(serviceEndDate: string): Promise<void> {
    await this.serviceEndDateInput.sendKeys(serviceEndDate);
  }

  async getServiceEndDateInput(): Promise<string> {
    return await this.serviceEndDateInput.getAttribute('value');
  }

  async setServicePriceInput(servicePrice: string): Promise<void> {
    await this.servicePriceInput.sendKeys(servicePrice);
  }

  async getServicePriceInput(): Promise<string> {
    return await this.servicePriceInput.getAttribute('value');
  }

  async dealsSelectLastOption(): Promise<void> {
    await this.dealsSelect.all(by.tagName('option')).last().click();
  }

  async dealsSelectOption(option: string): Promise<void> {
    await this.dealsSelect.sendKeys(option);
  }

  getDealsSelect(): ElementFinder {
    return this.dealsSelect;
  }

  async getDealsSelectedOption(): Promise<string> {
    return await this.dealsSelect.element(by.css('option:checked')).getText();
  }

  async packagesSelectLastOption(): Promise<void> {
    await this.packagesSelect.all(by.tagName('option')).last().click();
  }

  async packagesSelectOption(option: string): Promise<void> {
    await this.packagesSelect.sendKeys(option);
  }

  getPackagesSelect(): ElementFinder {
    return this.packagesSelect;
  }

  async getPackagesSelectedOption(): Promise<string> {
    return await this.packagesSelect.element(by.css('option:checked')).getText();
  }

  async accomodationsSelectLastOption(): Promise<void> {
    await this.accomodationsSelect.all(by.tagName('option')).last().click();
  }

  async accomodationsSelectOption(option: string): Promise<void> {
    await this.accomodationsSelect.sendKeys(option);
  }

  getAccomodationsSelect(): ElementFinder {
    return this.accomodationsSelect;
  }

  async getAccomodationsSelectedOption(): Promise<string> {
    return await this.accomodationsSelect.element(by.css('option:checked')).getText();
  }

  async activitiesSelectLastOption(): Promise<void> {
    await this.activitiesSelect.all(by.tagName('option')).last().click();
  }

  async activitiesSelectOption(option: string): Promise<void> {
    await this.activitiesSelect.sendKeys(option);
  }

  getActivitiesSelect(): ElementFinder {
    return this.activitiesSelect;
  }

  async getActivitiesSelectedOption(): Promise<string> {
    return await this.activitiesSelect.element(by.css('option:checked')).getText();
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

export class ServiceDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-service-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-service'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
