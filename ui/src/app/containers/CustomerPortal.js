import React, { useEffect } from "react";
import NavBar from "../components/NavBar";
import { Container } from "reactstrap";
import CustomerTable from "../components/CustomerTable";
import { connect } from "react-redux";
import {
  getCustomers,
  updateCustomer,
  getCustomerTransactionByMonth,
} from "../redux/reducers/customers";

function CustomerPortal({
  customers,
  customer,
  getCustomers,
  updateCustomer,
  points,
  selectedMonth,
  filteredCustomerTransactions,
  getCustomerTransactionByMonth,
}) {
  useEffect(() => {
    getCustomers();
  }, [getCustomers]);
  return (
    <React.Fragment>
      <NavBar
        customers={customers}
        currentCustomer={customer}
        updateCustomer={updateCustomer}
        points={points}
        getCustomerTransactionByMonth={getCustomerTransactionByMonth}
      />
      <Container className="themed-container" fluid="sm">
        <div className="App">
          <CustomerTable
            points={points}
            currentCustomer={customer}
            selectedMonth={selectedMonth}
            customerTransactions={filteredCustomerTransactions}
          />
        </div>
      </Container>
    </React.Fragment>
  );
}

const mapStateToProps = ({
  customers: {
    customers,
    customer,
    selectedMonth,
    points,
    filteredCustomerTransactions,
  },
}) => ({
  customers,
  customer,
  points,
  selectedMonth,
  filteredCustomerTransactions,
});
const mapDispatchToProps = {
  getCustomers,
  updateCustomer,

  getCustomerTransactionByMonth,
};

export default connect(mapStateToProps, mapDispatchToProps)(CustomerPortal);
