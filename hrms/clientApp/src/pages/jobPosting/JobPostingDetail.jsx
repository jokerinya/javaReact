import React, { useEffect, useState } from 'react';
import { useHistory, useParams } from 'react-router-dom';
import JobPostingService from '../../services/jobPostingService';
import { Button, Card } from 'semantic-ui-react';

function JobPostingDetail() {
  let { id } = useParams();
  let history = useHistory();

  const [jobPosting, setJobPosting] = useState({});

  useEffect(() => {
    let jobPostingService = new JobPostingService();
    jobPostingService.getById(id).then((result) => {
      setJobPosting(result.data.data);
    });
  }, [id]);

  const handleBackClick = () => {
    history.goBack();
  };

  return (
    <Card.Group>
      <Card fluid>
        <Card.Content>
          <Card.Header style={{ textTransform: 'capitalize' }}>
            {jobPosting.position?.positionName}
          </Card.Header>
          <Card.Meta>
            {jobPosting.company?.companyName} - {jobPosting.city?.cityName}
          </Card.Meta>
          <Card.Description>
            Last Application Date: {jobPosting.lastApplicationDate} <br />
            Wage: {jobPosting.minWage} - {jobPosting.maxWage} <br />
            Remote : {jobPosting.isRemote ? 'Yes' : 'No'} <br />
            Full-Time : {jobPosting.isPartTime ? 'No' : 'Yes'} <br />
            {jobPosting.description}
          </Card.Description>
        </Card.Content>
        <Card.Content extra>
          <div className='ui two buttons'>
            <Button basic color='green'>
              Apply to job
            </Button>
            <Button basic color='red' onClick={handleBackClick}>
              Back
            </Button>
          </div>
        </Card.Content>
      </Card>
    </Card.Group>
  );
}

export default JobPostingDetail;
