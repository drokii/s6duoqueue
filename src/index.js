import React from 'react';
import ReactDOM from 'react-dom';
import App from './app/App';
import * as serviceWorker from './serviceWorker';
import { BrowserRouter } from 'react-router-dom';

let axiosDefaults = require('axios/lib/defaults');
axiosDefaults.baseURL = 'http://localhost:8080/kwetter/rest/';
const app = (
    <BrowserRouter>
    <App />
    </BrowserRouter>
)
ReactDOM.render(app, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
