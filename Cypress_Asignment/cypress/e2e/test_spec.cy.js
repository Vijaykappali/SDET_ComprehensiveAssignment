describe('Automation Panda Cypress Test', () => {
  before(() => {
    cy.visit('https://automationpanda.com/2021/12/29/want-to-practice-test-automation-try-these-demo-sites/');
  });

  it('Verifies the title of the main page', () => {
    cy.title().should('include', 'Want to practice test automation');
  });

  it('Clicks on Speaking and verifies the title', () => {
    cy.contains('Speaking').click();
    cy.title().should('include', 'Speaking');
  });

  it('Checks if Keynote Addresses is present and verifies text', () => {
    cy.contains('Keynote Addresses').should('be.visible').and('have.text', 'Keynote Addresses');
  });
});
