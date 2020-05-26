import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TransportComponentsPage, TransportDeleteDialog, TransportUpdatePage } from './transport.page-object';

const expect = chai.expect;

describe('Transport e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let transportComponentsPage: TransportComponentsPage;
  let transportUpdatePage: TransportUpdatePage;
  let transportDeleteDialog: TransportDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Transports', async () => {
    await navBarPage.goToEntity('transport');
    transportComponentsPage = new TransportComponentsPage();
    await browser.wait(ec.visibilityOf(transportComponentsPage.title), 5000);
    expect(await transportComponentsPage.getTitle()).to.eq('travelAgencyApp.transport.home.title');
    await browser.wait(ec.or(ec.visibilityOf(transportComponentsPage.entities), ec.visibilityOf(transportComponentsPage.noResult)), 1000);
  });

  it('should load create Transport page', async () => {
    await transportComponentsPage.clickOnCreateButton();
    transportUpdatePage = new TransportUpdatePage();
    expect(await transportUpdatePage.getPageTitle()).to.eq('travelAgencyApp.transport.home.createOrEditLabel');
    await transportUpdatePage.cancel();
  });

  it('should create and save Transports', async () => {
    const nbButtonsBeforeCreate = await transportComponentsPage.countDeleteButtons();

    await transportComponentsPage.clickOnCreateButton();

    await promise.all([
      transportUpdatePage.transportTypeSelectLastOption(),
      transportUpdatePage.setTransportNameInput('transportName'),
      transportUpdatePage.setTransportDescriptionInput('transportDescription'),
      transportUpdatePage.toSelectLastOption(),
      transportUpdatePage.fromSelectLastOption(),
      // transportUpdatePage.serviceSelectLastOption(),
    ]);

    expect(await transportUpdatePage.getTransportNameInput()).to.eq(
      'transportName',
      'Expected TransportName value to be equals to transportName'
    );
    expect(await transportUpdatePage.getTransportDescriptionInput()).to.eq(
      'transportDescription',
      'Expected TransportDescription value to be equals to transportDescription'
    );

    await transportUpdatePage.save();
    expect(await transportUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await transportComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Transport', async () => {
    const nbButtonsBeforeDelete = await transportComponentsPage.countDeleteButtons();
    await transportComponentsPage.clickOnLastDeleteButton();

    transportDeleteDialog = new TransportDeleteDialog();
    expect(await transportDeleteDialog.getDialogTitle()).to.eq('travelAgencyApp.transport.delete.question');
    await transportDeleteDialog.clickOnConfirmButton();

    expect(await transportComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
