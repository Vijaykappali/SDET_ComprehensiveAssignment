// e2e.js - Cypress support file
// This runs before every test, useful for global configurations

before(() => {
    cy.log('Test execution started');
});

after(() => {
    cy.log('Test execution finished');
});
