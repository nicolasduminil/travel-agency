import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { AccomodationComponentsPage, AccomodationDeleteDialog, AccomodationUpdatePage } from './accomodation.page-object';

const expect = chai.expect;

describe('Accomodation e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let accomodationComponentsPage: AccomodationComponentsPage;
  let accomodationUpdatePage: AccomodationUpdatePage;
  let accomodationDeleteDialog: AccomodationDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Accomodations', async () => {
    await navBarPage.goToEntity('accomodation');
    accomodationComponentsPage = new AccomodationComponentsPage();
    await browser.wait(ec.visibilityOf(accomodationComponentsPage.title), 5000);
    expect(await accomodationComponentsPage.getTitle()).to.eq('travelAgencyApp.accomodation.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(accomodationComponentsPage.entities), ec.visibilityOf(accomodationComponentsPage.noResult)),
      1000
    );
  });

  it('should load create Accomodation page', async () => {
    await accomodationComponentsPage.clickOnCreateButton();
    accomodationUpdatePage = new AccomodationUpdatePage();
    expect(await accomodationUpdatePage.getPageTitle()).to.eq('travelAgencyApp.accomodation.home.createOrEditLabel');
    await accomodationUpdatePage.cancel();
  });

  it('should create and save Accomodations', async () => {
    const nbButtonsBeforeCreate = await accomodationComponentsPage.countDeleteButtons();

    await accomodationComponentsPage.clickOnCreateButton();

    await promise.all([
      accomodationUpdatePage.setAccomodationNameInput('accomodationName'),
      accomodationUpdatePage.accomodationTypeSelectLastOption(),
      accomodationUpdatePage.accomodationClassSelectLastOption(),
      accomodationUpdatePage.locationSelectLastOption(),
    ]);

    expect(await accomodationUpdatePage.getAccomodationNameInput()).to.eq(
      'accomodationName',
      'Expected AccomodationName value to be equals to accomodationName'
    );

    await accomodationUpdatePage.save();
    expect(await accomodationUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await accomodationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Accomodation', async () => {
    const nbButtonsBeforeDelete = await accomodationComponentsPage.countDeleteButtons();
    await accomodationComponentsPage.clickOnLastDeleteButton();

    accomodationDeleteDialog = new AccomodationDeleteDialog();
    expect(await accomodationDeleteDialog.getDialogTitle()).to.eq('travelAgencyApp.accomodation.delete.question');
    await accomodationDeleteDialog.clickOnConfirmButton();

    expect(await accomodationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
