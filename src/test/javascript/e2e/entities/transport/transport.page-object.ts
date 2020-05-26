import { element, by, ElementFinder } from 'protractor';

export class TransportComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-transport div table .btn-danger'));
  title = element.all(by.css('jhi-transport div h2#page-heading span')).first();
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

export class TransportUpdatePage {
  pageTitle = element(by.id('jhi-transport-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  transportTypeSelect = element(by.id('field_transportType'));
  transportNameInput = element(by.id('field_transportName'));
  transportDescriptionInput = element(by.id('field_transportDescription'));

  toSelect = element(by.id('field_to'));
  fromSelect = element(by.id('field_from'));
  serviceSelect = element(by.id('field_service'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTransportTypeSelect(transportType: string): Promise<void> {
    await this.transportTypeSelect.sendKeys(transportType);
  }

  async getTransportTypeSelect(): Promise<string> {
    return await this.transportTypeSelect.element(by.css('option:checked')).getText();
  }

  async transportTypeSelectLastOption(): Promise<void> {
    await this.transportTypeSelect.all(by.tagName('option')).last().click();
  }

  async setTransportNameInput(transportName: string): Promise<void> {
    await this.transportNameInput.sendKeys(transportName);
  }

  async getTransportNameInput(): Promise<string> {
    return await this.transportNameInput.getAttribute('value');
  }

  async setTransportDescriptionInput(transportDescription: string): Promise<void> {
    await this.transportDescriptionInput.sendKeys(transportDescription);
  }

  async getTransportDescriptionInput(): Promise<string> {
    return await this.transportDescriptionInput.getAttribute('value');
  }

  async toSelectLastOption(): Promise<void> {
    await this.toSelect.all(by.tagName('option')).last().click();
  }

  async toSelectOption(option: string): Promise<void> {
    await this.toSelect.sendKeys(option);
  }

  getToSelect(): ElementFinder {
    return this.toSelect;
  }

  async getToSelectedOption(): Promise<string> {
    return await this.toSelect.element(by.css('option:checked')).getText();
  }

  async fromSelectLastOption(): Promise<void> {
    await this.fromSelect.all(by.tagName('option')).last().click();
  }

  async fromSelectOption(option: string): Promise<void> {
    await this.fromSelect.sendKeys(option);
  }

  getFromSelect(): ElementFinder {
    return this.fromSelect;
  }

  async getFromSelectedOption(): Promise<string> {
    return await this.fromSelect.element(by.css('option:checked')).getText();
  }

  async serviceSelectLastOption(): Promise<void> {
    await this.serviceSelect.all(by.tagName('option')).last().click();
  }

  async serviceSelectOption(option: string): Promise<void> {
    await this.serviceSelect.sendKeys(option);
  }

  getServiceSelect(): ElementFinder {
    return this.serviceSelect;
  }

  async getServiceSelectedOption(): Promise<string> {
    return await this.serviceSelect.element(by.css('option:checked')).getText();
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

export class TransportDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-transport-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-transport'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
