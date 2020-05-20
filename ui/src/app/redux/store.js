import { configureStore } from '@reduxjs/toolkit';
import customers from './reducers/customers';

export default configureStore({
  reducer: {
    customers,
  },
});
