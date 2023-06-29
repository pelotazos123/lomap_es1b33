import './index.css';
import App from './App';
import React from 'react';
import ReactDOM from 'react-dom';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from 'react-router-dom';
import { SessionProvider } from "@inrupt/solid-ui-react";
import { MarkerContextProvider } from './context/MarkerContextProvider';
import { NotificationsProvider } from 'reapop';
import "./localize/i18n";

ReactDOM.render(
  <React.StrictMode>
    <NotificationsProvider>
      <SessionProvider session-id="login" restorePreviousSession={true}>
        <MarkerContextProvider>
          <BrowserRouter>
            <App />
          </BrowserRouter>
        </MarkerContextProvider>
      </SessionProvider>
    </NotificationsProvider>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
