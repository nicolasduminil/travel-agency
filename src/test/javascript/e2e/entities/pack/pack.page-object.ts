import { element, by, ElementFinder } from 'protractor';

export class PackComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pack div table .btn-danger'));
  title = element.all(by.css('jhi-pack div h2#page-heading span')).first();
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

export class PackUpdatePage {
  pageTitle = element(by.id('jhi-pack-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  packageNameInput = element(by.id('field_packageName'));
  packageDescriptionInput = element(by.id('field_packageDescription'));
  packageDiscountInput = element(by.id('field_packageDiscount'));
  packagePriceInput = element(by.id('field_packagePrice'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setPackageNameInput(packageName: string): Promise<void> {
    await this.packageNameInput.sendKeys(packageName);
  }

  async getPackageNameInput(): Promise<string> {
    return await this.packageNameInput.getAttribute('value');
  }

  async setPackageDescriptionInput(packageDescription: string): Promise<void> {
    await this.packageDescriptionInput.sendKeys(packageDescription);
  }

  async getPackageDescriptionInput(): Promise<string> {
    return await this.packageDescriptionInput.getAttribute('value');
  }

  async setPackageDiscountInput(packageDiscount: string): Promise<void> {
    await this.packageDiscountInput.sendKeys(packageDiscount);
  }

  async getPackageDiscountInput(): Promise<string> {
    return await this.packageDiscountInput.getAttribute('value');
  }

  async setPackagePriceInput(packagePrice: string): Promise<void> {
    await this.packagePriceInput.sendKeys(packagePrice);
  }

  async getPackagePriceInput(): Promise<string> {
    return await this.packagePriceInput.getAttribute('value');
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

export class PackDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-pack-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-pack'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
