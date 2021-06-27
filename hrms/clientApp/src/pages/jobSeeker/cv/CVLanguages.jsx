import React from 'react';
import { Card, Icon, Label, List } from 'semantic-ui-react';

function CvLanguages({ languages }) {
  return (
    <Card.Description>
      <List celled>
        {languages?.length > 0 ? (
          languages.map((language) => (
            <List.Item key={language.knownLanguageId}>
              <Icon name={'language'} size={'big'} />
              <List.Content>
                <List.Header style={{ textTransform: 'capitalize' }}>
                  {language.language?.languageName}{' '}
                  <Label circular color={'purple'}>
                    {language.languageLevel}/5
                  </Label>
                </List.Header>
              </List.Content>
            </List.Item>
          ))
        ) : (
          <List.Item>No Known Languages</List.Item>
        )}
      </List>
    </Card.Description>
  );
}

export default CvLanguages;
