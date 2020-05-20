import React, { useState } from "react";
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
  NavbarText,
} from "reactstrap";

const NavBar = ({
  customers,
  currentCustomer,
  updateCustomer,
  points,
  getCustomerTransactionByMonth,
}) => {
  const [isOpen, setIsOpen] = useState(false);

  const toggle = () => setIsOpen(!isOpen);

  const handleSelectedCustomer = (customer) => {
    updateCustomer(customer);
    getCustomerTransactionByMonth("All");
  };

  const handleSelectedMonth = (month) => {
    getCustomerTransactionByMonth(month);
  };
  const months = ["January", "Febuary", "March"];
  return (
    <div className="mb-5">
      <Navbar color="info" dark expand="md">
        <NavbarBrand color="" href="/">
          Customer Portal
        </NavbarBrand>
        <NavbarToggler onClick={toggle} />
        <Collapse isOpen={isOpen} navbar>
          <Nav className="mr-auto" navbar>
            {customers ? (
              <UncontrolledDropdown nav inNavbar>
                <DropdownToggle nav caret>
                  Customers
                </DropdownToggle>
                <DropdownMenu right>
                  {customers.map((customer) => (
                    <DropdownItem
                      key={customer.id}
                      onClick={() => handleSelectedCustomer(customer)}
                    >
                      {customer.name}
                    </DropdownItem>
                  ))}
                </DropdownMenu>
              </UncontrolledDropdown>
            ) : null}
            {customers ? (
              <UncontrolledDropdown nav inNavbar>
                <DropdownToggle nav caret>
                  Customer Points
                </DropdownToggle>
                <DropdownMenu right>
                  {months.map((month) => (
                    <DropdownItem
                      key={month}
                      onClick={() => handleSelectedMonth(month)}
                    >
                      {month}
                    </DropdownItem>
                  ))}
                  <DropdownItem divider />
                  <DropdownItem onClick={() => handleSelectedMonth("All")}>
                    All
                  </DropdownItem>
                </DropdownMenu>
              </UncontrolledDropdown>
            ) : null}
          </Nav>
          <NavbarText className="mr-3">
            <b>{currentCustomer ? currentCustomer["name"] : ""}</b>
          </NavbarText>
          <NavbarText className="mr-5">
            <b>Total Points : {points}</b>
          </NavbarText>
        </Collapse>
      </Navbar>
    </div>
  );
};

export default NavBar;
