import React, { useEffect, useState } from 'react';
import { Dropdown } from 'semantic-ui-react';
import CompanyService from '../../services/companyService';

export default function CompanyFilter() {
  const [companies, setCompanies] = useState([]);

  const companyOptions = companies.map((company) => ({
    key: company.userId,
    value: company.userId,
    text: company.companyName,
  }));

  useEffect(() => {
    let mounted = true;
    let companyService = new CompanyService();
    companyService.getAll().then((result) => {
      if (mounted) {
        setCompanies(result.data.data);
      }
    });
    return () => (mounted = false);
  }, []);

  function getCompanyId(event, data) {
    // Select Company id, later we will send it to redux for re render
    console.log(data.value);
  }

  return (
    <>
      <Dropdown
        placeholder='Select Position'
        fluid
        search
        selection
        onChange={getCompanyId}
        options={companyOptions}
      />
    </>
  );
}
