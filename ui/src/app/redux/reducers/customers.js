import axios from "axios";
import moment from "moment";
import { monthByName } from "../../util/monthUtil";
import { ACTION_TYPES } from "../actions";

axios.defaults.baseURL = "http://localhost:8080/api";

const initialState = {
  customers: [],
  customer: undefined,
  customerTransactions: [],
  filteredCustomerTransactions: [],
  selectedMonth: "",
  points: 0,
};

export default (state = initialState, action) => {
  switch (action.type) {
    case ACTION_TYPES.GET_ALL_CUSTOMERS: {
      return {
        ...state,
        points: action.payload.points,
        customers: action.payload.data,
      };
    }
    case ACTION_TYPES.GET_CUSTOMER_TRANSACTIONS: {
      return {
        ...state,
        customerTransactions: action.payload,
      };
    }
    case ACTION_TYPES.GET_CUSTOMER_TRANSACTION_BY_MONTH: {
      return {
        ...state,
        points: action.payload.totalMonthPoints,
        selectedMonth: action.payload.selectedMonth,
        filteredCustomerTransactions:
          action.payload.filteredCustomerTransactions,
      };
    }
    case ACTION_TYPES.UPDATE_CUSTOMER: {
      return {
        ...state,
        customer: action.payload,
      };
    }
    case ACTION_TYPES.UPDATE_MONTH: {
      return {
        ...state,
        selectedMonth: action.payload,
      };
    }
    default:
      return state;
  }
};

export const getCustomers = () => async (dispatch, getState) => {
  const { data } = await axios.get("/customers");
  const defaultCustomer = data[0];
  dispatch(updateCustomer(defaultCustomer));

  dispatch({
    type: ACTION_TYPES.GET_ALL_CUSTOMERS,
    payload: { data, points: defaultCustomer.account.points },
  });
  dispatch(getCustomersTransactions(defaultCustomer));
  dispatch(getCustomerTransactionByMonth("All"));
};

export const updateCustomer = (customer) => (dispatch, getState) => {
  dispatch({
    type: ACTION_TYPES.UPDATE_CUSTOMER,
    payload: customer,
  });

  dispatch(getCustomersTransactions(customer));
};

export const getCustomersTransactions = (currentCustomer) => async (
  dispatch,
  getState
) => {
  const {
    customers: { customers },
  } = getState();

  const customerTransactions = customers
    .filter((customer) => customer.id === currentCustomer.id)
    .map((customer) => customer.account.customerTransactions)
    .flatMap((customer) => customer);

  console.log("cusomerTransaction", customerTransactions);

  await dispatch({
    type: ACTION_TYPES.GET_CUSTOMER_TRANSACTIONS,
    payload: customerTransactions,
  });
};

export const getCustomerTransactionByMonth = (selectedMonth) => async (
  dispatch,
  getState
) => {
  const {
    customers: { customerTransactions },
  } = getState();

  const filteredCustomerTransactions =
    selectedMonth === "All"
      ? customerTransactions
      : customerTransactions.filter((trans) => {
          const month = 1 + moment(trans.transactionDate).month();
          return selectedMonth === monthByName[month];
        });

  const totalMonthPoints = filteredCustomerTransactions.reduce(
    (totalPoint, transaction) => {
      return totalPoint + transaction.points;
    },
    0
  );

  await dispatch({
    type: ACTION_TYPES.GET_CUSTOMER_TRANSACTION_BY_MONTH,
    payload: { filteredCustomerTransactions, selectedMonth, totalMonthPoints },
  });
};
