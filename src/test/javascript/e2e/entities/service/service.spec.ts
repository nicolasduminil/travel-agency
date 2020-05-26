import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ServiceComponentsPage, ServiceDeleteDialog, ServiceUpdatePage } from './service.page-object';

const expect = chai.expect;

describe('Service e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let serviceComponentsPage: ServiceComponentsPage;
  let serviceUpdatePage: ServiceUpdatePage;
  let serviceDeleteDialog: ServiceDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Services', async () => {
    await navBarPage.goToEntity('service');
    serviceComponentsPage = new ServiceComponentsPage();
    await browser.wait(ec.visibilityOf(serviceComponentsPage.title), 5000);
    expect(await serviceComponentsPage.getTitle()).to.eq('travelAgencyApp.service.home.title');
    await browser.wait(ec.or(ec.visibilityOf(serviceComponentsPage.entities), ec.visibilityOf(serviceComponentsPage.noResult)), 1000);
  });

  it('should load create Service page', async () => {
    await serviceComponentsPage.clickOnCreateButton();
    serviceUpdatePage = new ServiceUpdatePage();
    expect(await serviceUpdatePage.getPageTitle()).to.eq('travelAgencyApp.service.home.createOrEditLabel');
    await serviceUpdatePage.cancel();
  });

  it('should create and save Services', async () => {
    const nbButtonsBeforeCreate = await serviceComponentsPage.countDeleteButtons();

    await serviceComponentsPage.clickOnCreateButton();

    await promise.all([
      serviceUpdatePage.setServiceDescriptionInput('serviceDescription'),
      serviceUpdatePage.setServiceStartDateInput('2000-12-31'),
      serviceUpdatePage.setServiceEndDateInput('2000-12-31'),
      serviceUpdatePage.setServicePriceInput('5'),
      // serviceUpdatePage.dealsSelectLastOption(),
      // serviceUpdatePage.packagesSelectLastOption(),
      // serviceUpdatePage.accomodationsSelectLastOption(),
      // serviceUpdatePage.activitiesSelectLastOption(),
    ]);

    expect(await serviceUpdatePage.getServiceDescriptionInput()).to.eq(
      'serviceDescription',
      'Expected ServiceDescription value to be equals to serviceDescription'
    );
    expect(await serviceUpdatePage.getServiceStartDateInput()).to.eq(
      '2000-12-31',
      'Expected serviceStartDate value to be equals to 2000-12-31'
    );
    expect(await serviceUpdatePage.getServiceEndDateInput()).to.eq(
      '2000-12-31',
      'Expected serviceEndDate value to be equals to 2000-12-31'
    );
    expect(await serviceUpdatePage.getServicePriceInput()).to.eq('5', 'Expected servicePrice value to be equals to 5');

    await serviceUpdatePage.save();
    expect(await serviceUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await serviceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Service', async () => {
    const nbButtonsBeforeDelete = await serviceComponentsPage.countDeleteButtons();
    await serviceComponentsPage.clickOnLastDeleteButton();

    serviceDeleteDialog = new ServiceDeleteDialog();
    expect(await serviceDeleteDialog.getDialogTitle()).to.eq('travelAgencyApp.service.delete.question');
    await serviceDeleteDialog.clickOnConfirmButton();

    expect(await serviceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
