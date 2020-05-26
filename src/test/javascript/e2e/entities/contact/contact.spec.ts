import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ContactComponentsPage, ContactDeleteDialog, ContactUpdatePage } from './contact.page-object';

const expect = chai.expect;

describe('Contact e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let contactComponentsPage: ContactComponentsPage;
  let contactUpdatePage: ContactUpdatePage;
  let contactDeleteDialog: ContactDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Contacts', async () => {
    await navBarPage.goToEntity('contact');
    contactComponentsPage = new ContactComponentsPage();
    await browser.wait(ec.visibilityOf(contactComponentsPage.title), 5000);
    expect(await contactComponentsPage.getTitle()).to.eq('travelAgencyApp.contact.home.title');
    await browser.wait(ec.or(ec.visibilityOf(contactComponentsPage.entities), ec.visibilityOf(contactComponentsPage.noResult)), 1000);
  });

  it('should load create Contact page', async () => {
    await contactComponentsPage.clickOnCreateButton();
    contactUpdatePage = new ContactUpdatePage();
    expect(await contactUpdatePage.getPageTitle()).to.eq('travelAgencyApp.contact.home.createOrEditLabel');
    await contactUpdatePage.cancel();
  });

  it('should create and save Contacts', async () => {
    const nbButtonsBeforeCreate = await contactComponentsPage.countDeleteButtons();

    await contactComponentsPage.clickOnCreateButton();

    await promise.all([
      contactUpdatePage.setContactNameInput('contactName'),
      contactUpdatePage.setContactFirstNameInput('contactFirstName'),
      contactUpdatePage.setContactLastNameInput('contactLastName'),
      contactUpdatePage.setContactEmailAddressInput('v%|@q-J.ScGWfr'),
      contactUpdatePage.setContactWebSiteInput('contactWebSite'),
      contactUpdatePage.contactSalutationSelectLastOption(),
      contactUpdatePage.setContactJobTitleInput('contactJobTitle'),
      contactUpdatePage.setContactPhoneNumberInput('contactPhoneNumber'),
      contactUpdatePage.setContactFaxNumberInput('contactFaxNumber'),
      contactUpdatePage.addressSelectLastOption(),
      contactUpdatePage.activitySelectLastOption(),
      contactUpdatePage.customerSelectLastOption(),
      contactUpdatePage.dealSelectLastOption(),
    ]);

    expect(await contactUpdatePage.getContactNameInput()).to.eq('contactName', 'Expected ContactName value to be equals to contactName');
    expect(await contactUpdatePage.getContactFirstNameInput()).to.eq(
      'contactFirstName',
      'Expected ContactFirstName value to be equals to contactFirstName'
    );
    expect(await contactUpdatePage.getContactLastNameInput()).to.eq(
      'contactLastName',
      'Expected ContactLastName value to be equals to contactLastName'
    );
    expect(await contactUpdatePage.getContactEmailAddressInput()).to.eq(
      'v%|@q-J.ScGWfr',
      'Expected ContactEmailAddress value to be equals to v%|@q-J.ScGWfr'
    );
    expect(await contactUpdatePage.getContactWebSiteInput()).to.eq(
      'contactWebSite',
      'Expected ContactWebSite value to be equals to contactWebSite'
    );
    expect(await contactUpdatePage.getContactJobTitleInput()).to.eq(
      'contactJobTitle',
      'Expected ContactJobTitle value to be equals to contactJobTitle'
    );
    expect(await contactUpdatePage.getContactPhoneNumberInput()).to.eq(
      'contactPhoneNumber',
      'Expected ContactPhoneNumber value to be equals to contactPhoneNumber'
    );
    expect(await contactUpdatePage.getContactFaxNumberInput()).to.eq(
      'contactFaxNumber',
      'Expected ContactFaxNumber value to be equals to contactFaxNumber'
    );

    await contactUpdatePage.save();
    expect(await contactUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await contactComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Contact', async () => {
    const nbButtonsBeforeDelete = await contactComponentsPage.countDeleteButtons();
    await contactComponentsPage.clickOnLastDeleteButton();

    contactDeleteDialog = new ContactDeleteDialog();
    expect(await contactDeleteDialog.getDialogTitle()).to.eq('travelAgencyApp.contact.delete.question');
    await contactDeleteDialog.clickOnConfirmButton();

    expect(await contactComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
