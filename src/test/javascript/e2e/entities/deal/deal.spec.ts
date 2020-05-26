import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DealComponentsPage, DealDeleteDialog, DealUpdatePage } from './deal.page-object';

const expect = chai.expect;

describe('Deal e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let dealComponentsPage: DealComponentsPage;
  let dealUpdatePage: DealUpdatePage;
  let dealDeleteDialog: DealDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Deals', async () => {
    await navBarPage.goToEntity('deal');
    dealComponentsPage = new DealComponentsPage();
    await browser.wait(ec.visibilityOf(dealComponentsPage.title), 5000);
    expect(await dealComponentsPage.getTitle()).to.eq('travelAgencyApp.deal.home.title');
    await browser.wait(ec.or(ec.visibilityOf(dealComponentsPage.entities), ec.visibilityOf(dealComponentsPage.noResult)), 1000);
  });

  it('should load create Deal page', async () => {
    await dealComponentsPage.clickOnCreateButton();
    dealUpdatePage = new DealUpdatePage();
    expect(await dealUpdatePage.getPageTitle()).to.eq('travelAgencyApp.deal.home.createOrEditLabel');
    await dealUpdatePage.cancel();
  });

  it('should create and save Deals', async () => {
    const nbButtonsBeforeCreate = await dealComponentsPage.countDeleteButtons();

    await dealComponentsPage.clickOnCreateButton();

    await promise.all([dealUpdatePage.setDealNameInput('dealName'), dealUpdatePage.setDealBookDateInput('2000-12-31')]);

    expect(await dealUpdatePage.getDealNameInput()).to.eq('dealName', 'Expected DealName value to be equals to dealName');
    expect(await dealUpdatePage.getDealBookDateInput()).to.eq('2000-12-31', 'Expected dealBookDate value to be equals to 2000-12-31');

    await dealUpdatePage.save();
    expect(await dealUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await dealComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Deal', async () => {
    const nbButtonsBeforeDelete = await dealComponentsPage.countDeleteButtons();
    await dealComponentsPage.clickOnLastDeleteButton();

    dealDeleteDialog = new DealDeleteDialog();
    expect(await dealDeleteDialog.getDialogTitle()).to.eq('travelAgencyApp.deal.delete.question');
    await dealDeleteDialog.clickOnConfirmButton();

    expect(await dealComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
