import React from "react";
import { Table, Col, Row } from "reactstrap";

const CustomerTable = ({ customerTransactions, selectedMonth, points }) => {
  return (
    <Row style={{ color: "green", fontWeight: "strong" }}>
      <Col xs="4">
        <div>
          <span className="display-4">{" Total Points"} :</span>
          <h1 className="display-1">{points}</h1>
        </div>
      </Col>
      <Col xs="8">
        <div className="mb-3">
          <h3 className="mb-2  align-self-center">
            <strong>
              {selectedMonth ? `${selectedMonth}` : "All"} Transactions
            </strong>
          </h3>
        </div>
        <Table striped style={{ color: "green", fontWeight: "strong" }}>
          <thead>
            <tr>
              <th>#</th>
              <th>Transaction Amount</th>
              <th>Transaction Points</th>
              <th>Transaction Date</th>
            </tr>
          </thead>
          <tbody>
            {customerTransactions.map((transaction, index) => {
              return (
                <tr key={index + 1}>
                  <th scope="row">{index + 1}</th>
                  <td>${transaction.amount}</td>
                  <td>{transaction.points}</td>
                  <td>{transaction.transactionDate}</td>
                </tr>
              );
            })}
          </tbody>
        </Table>
      </Col>
    </Row>
  );
};

export default CustomerTable;
