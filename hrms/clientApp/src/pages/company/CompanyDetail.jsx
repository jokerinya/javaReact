import React, { useEffect, useState } from 'react';
import { useHistory, useParams } from 'react-router-dom';
import CompanyService from '../../services/companyService';
import { Button, Card, Icon } from 'semantic-ui-react';
import { useDispatch } from 'react-redux';
import { updateQueryParam } from '../../store/actions/jobQueryParamsActions';

function CompanyDetail() {
  let { id } = useParams();
  const [company, setCompany] = useState({});
  let history = useHistory();
  const dispatch = useDispatch();

  useEffect(() => {
    let companyService = new CompanyService();
    companyService
      .getByCompanyId(id)
      .then((result) => setCompany(result.data.data));
  }, [id]);

  const handleClick = (id) => {
    dispatch(updateQueryParam(`?companyId=${id}`));
    history.push('/');
  };

  return (
    <Card.Group>
      <Card fluid>
        <Card.Content>
          <Card.Header style={{ textTransform: 'capitalize' }}>
            {company.companyName}
          </Card.Header>
          <Card.Description>
            Email Address: {company.email} <br />
            Phone: {company.companyPhone} <br />
          </Card.Description>
        </Card.Content>
        <Card.Content extra>
          <div className='ui two buttons'>
            <Button
              basic
              color='green'
              onClick={() => handleClick(company.userId)}
            >
              See Job Postings of this company
            </Button>
            <Button basic color='red'>
              <a href={company.companyWebsite} target='_blank' rel='noreferrer'>
                Visit company site &nbsp;
                <sup>
                  <Icon name='external alternate' />
                </sup>
              </a>
            </Button>
          </div>
        </Card.Content>
      </Card>
    </Card.Group>
  );
}

export default CompanyDetail;
