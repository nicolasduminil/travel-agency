import { element, by, ElementFinder } from 'protractor';

export class DealComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-deal div table .btn-danger'));
  title = element.all(by.css('jhi-deal div h2#page-heading span')).first();
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

export class DealUpdatePage {
  pageTitle = element(by.id('jhi-deal-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  dealNameInput = element(by.id('field_dealName'));
  dealBookDateInput = element(by.id('field_dealBookDate'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setDealNameInput(dealName: string): Promise<void> {
    await this.dealNameInput.sendKeys(dealName);
  }

  async getDealNameInput(): Promise<string> {
    return await this.dealNameInput.getAttribute('value');
  }

  async setDealBookDateInput(dealBookDate: string): Promise<void> {
    await this.dealBookDateInput.sendKeys(dealBookDate);
  }

  async getDealBookDateInput(): Promise<string> {
    return await this.dealBookDateInput.getAttribute('value');
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

export class DealDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-deal-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-deal'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
