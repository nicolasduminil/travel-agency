import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PackComponentsPage, PackDeleteDialog, PackUpdatePage } from './pack.page-object';

const expect = chai.expect;

describe('Pack e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let packComponentsPage: PackComponentsPage;
  let packUpdatePage: PackUpdatePage;
  let packDeleteDialog: PackDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Packs', async () => {
    await navBarPage.goToEntity('pack');
    packComponentsPage = new PackComponentsPage();
    await browser.wait(ec.visibilityOf(packComponentsPage.title), 5000);
    expect(await packComponentsPage.getTitle()).to.eq('travelAgencyApp.pack.home.title');
    await browser.wait(ec.or(ec.visibilityOf(packComponentsPage.entities), ec.visibilityOf(packComponentsPage.noResult)), 1000);
  });

  it('should load create Pack page', async () => {
    await packComponentsPage.clickOnCreateButton();
    packUpdatePage = new PackUpdatePage();
    expect(await packUpdatePage.getPageTitle()).to.eq('travelAgencyApp.pack.home.createOrEditLabel');
    await packUpdatePage.cancel();
  });

  it('should create and save Packs', async () => {
    const nbButtonsBeforeCreate = await packComponentsPage.countDeleteButtons();

    await packComponentsPage.clickOnCreateButton();

    await promise.all([
      packUpdatePage.setPackageNameInput('packageName'),
      packUpdatePage.setPackageDescriptionInput('packageDescription'),
      packUpdatePage.setPackageDiscountInput('5'),
      packUpdatePage.setPackagePriceInput('5'),
    ]);

    expect(await packUpdatePage.getPackageNameInput()).to.eq('packageName', 'Expected PackageName value to be equals to packageName');
    expect(await packUpdatePage.getPackageDescriptionInput()).to.eq(
      'packageDescription',
      'Expected PackageDescription value to be equals to packageDescription'
    );
    expect(await packUpdatePage.getPackageDiscountInput()).to.eq('5', 'Expected packageDiscount value to be equals to 5');
    expect(await packUpdatePage.getPackagePriceInput()).to.eq('5', 'Expected packagePrice value to be equals to 5');

    await packUpdatePage.save();
    expect(await packUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await packComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Pack', async () => {
    const nbButtonsBeforeDelete = await packComponentsPage.countDeleteButtons();
    await packComponentsPage.clickOnLastDeleteButton();

    packDeleteDialog = new PackDeleteDialog();
    expect(await packDeleteDialog.getDialogTitle()).to.eq('travelAgencyApp.pack.delete.question');
    await packDeleteDialog.clickOnConfirmButton();

    expect(await packComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
