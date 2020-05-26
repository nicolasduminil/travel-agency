import { element, by, ElementFinder } from 'protractor';

export class ActivityComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-activity div table .btn-danger'));
  title = element.all(by.css('jhi-activity div h2#page-heading span')).first();
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

export class ActivityUpdatePage {
  pageTitle = element(by.id('jhi-activity-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  activityDescriptionInput = element(by.id('field_activityDescription'));
  activityTypeSelect = element(by.id('field_activityType'));

  locationSelect = element(by.id('field_location'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setActivityDescriptionInput(activityDescription: string): Promise<void> {
    await this.activityDescriptionInput.sendKeys(activityDescription);
  }

  async getActivityDescriptionInput(): Promise<string> {
    return await this.activityDescriptionInput.getAttribute('value');
  }

  async setActivityTypeSelect(activityType: string): Promise<void> {
    await this.activityTypeSelect.sendKeys(activityType);
  }

  async getActivityTypeSelect(): Promise<string> {
    return await this.activityTypeSelect.element(by.css('option:checked')).getText();
  }

  async activityTypeSelectLastOption(): Promise<void> {
    await this.activityTypeSelect.all(by.tagName('option')).last().click();
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

export class ActivityDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-activity-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-activity'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
