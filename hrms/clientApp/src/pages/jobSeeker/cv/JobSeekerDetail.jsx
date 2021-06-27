import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import CVService from '../../../services/cv/cvService';
import NotFound from '../../NotFound';
import { Card, Icon, Image, Label } from 'semantic-ui-react';
import CVExperiences from './CVExperiences';
import CVSchools from './CVSchools';
import CvLanguages from './CVLanguages';
import CvSocialMedia from './CVSocialMedia';

function JobSeekerDetail() {
  let { id } = useParams();
  const [cv, setCv] = useState({});

  useEffect(() => {
    let cvService = new CVService();
    cvService.getJobSeekerCv(id).then((result) => {
      setCv(result.data.data);
    });
  }, [setCv, id]);

  return (
    <>
      {!cv ? (
        <NotFound />
      ) : (
        <>
          <Card.Group>
            <Card fluid color='blue'>
              <Card.Content>
                <Card.Header style={{ textTransform: 'capitalize' }}>
                  {cv.jobSeeker?.jobSeekerImage?.imageUrl ? (
                    <Image
                      src={cv.jobSeeker?.jobSeekerImage?.imageUrl}
                      size={'small'}
                      circular
                    />
                  ) : (
                    <Icon name={'user'} size={'huge'} circular />
                  )}
                  <span>{`${cv.jobSeeker?.firstName} ${cv.jobSeeker?.lastName}`}</span>
                </Card.Header>
                <Card.Meta>
                  {cv.technologies?.map((tech) => (
                    <Label key={tech.technologyId} color={'blue'}>
                      {tech.technologyName}
                    </Label>
                  ))}
                </Card.Meta>
                <Card.Description>
                  {cv.forewords?.length > 0
                    ? cv.forewords[0].forewordBody
                    : cv.forewords?.forewordBody}
                </Card.Description>
              </Card.Content>
            </Card>
            {/*Experiences*/}
            <Card fluid color='green'>
              <Card.Content>
                <Card.Header content='Experiences' />
                <CVExperiences experiences={cv.jobExperiences} />
              </Card.Content>
            </Card>
            {/* Graduated Schools */}
            <Card fluid color='olive'>
              <Card.Content>
                <Card.Header content='Education Information' />
                <CVSchools schools={cv.graduatedSchools} />
              </Card.Content>
            </Card>
            {/*  Known Languages */}
            <Card fluid color='purple'>
              <Card.Content>
                <Card.Header content='Known Languages' />
                <CvLanguages languages={cv.knownLanguages} />
              </Card.Content>
            </Card>
            {/*  Social Media Addresses */}
            <Card fluid color='purple'>
              <Card.Content>
                <Card.Header content='Social Media Platforms' />
                <CvSocialMedia address={cv.socialMediaAddress} />
              </Card.Content>
            </Card>
          </Card.Group>
        </>
      )}
    </>
  );
}

export default JobSeekerDetail;
