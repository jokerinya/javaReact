import React, { useEffect, useState } from 'react';
import { Dropdown } from 'semantic-ui-react';
import PositionService from '../../services/positionService';
import TextEditorService from '../../utils/textFormatting/textEditorService';

export default function PositionFilter(props) {
  const [positions, setPositions] = useState([]);

  const positionOptions = positions.map((position) => ({
    key: position.positionId,
    value: position.positionId,
    text: TextEditorService.capitalize(position.positionName),
  }));

  useEffect(() => {
    let positionService = new PositionService();
    positionService.getAll().then((result) => {
      setPositions(result.data.data);
    });
  }, [setPositions]);

  function getPositionId(event, data) {
    // Select position id, later we will send it to redux for re render
    props.getPositionId(data.value);
  }

  return (
    <>
      <Dropdown
        placeholder='Select Position'
        clearable
        fluid
        search
        selection
        onChange={getPositionId}
        options={positionOptions}
      />
    </>
  );
}
