import { defineFeature, loadFeature } from 'jest-cucumber';
import puppeteer from "puppeteer";

const feature = loadFeature('./features/login.feature');

let page: puppeteer.Page;
let browser: puppeteer.Browser;

defineFeature(feature, test => {
  
  beforeAll(async () => {
    browser = process.env.GITHUB_ACTIONS
      ? await puppeteer.launch()
      : await puppeteer.launch({ headless: false, slowMo: 50 });
    page = await browser.newPage();

    await page
      .goto(process.env.REACT_APP_APP_URI ?? "http://localhost:3000", {
        waitUntil: "networkidle0",
      })
      .catch(() => {});
  });

  test('The user signs in', ({given,when,then}) => {
    
    let email:string;
    let username:string;

    given('An user in the homepage', async () => {
      const text = await page.evaluate(() => document.body.textContent);
      expect(text).toContain('HomeView.welcome')
    });

    when('User clicks on login button', async () => {
      await expect(page).toClick('button', { text: 'NavBar.open' })
    });

    then('Login dialog should be opened', async () => {
      const text = await page.evaluate(() => document.body.textContent);
      expect(text).toContain('Login.select')
    });
  })

  afterAll(async ()=>{
    browser.close()
  })

});

