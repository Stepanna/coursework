import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './wish-list.reducer';
import { IWishList } from 'app/shared/model/wish-list.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IWishListDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class WishListDetail extends React.Component<IWishListDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { wishListEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            WishList [<b>{wishListEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="title">Title</span>
            </dt>
            <dd>{wishListEntity.title}</dd>
            <dt>
              <span id="restricted">Restricted</span>
            </dt>
            <dd>{wishListEntity.restricted ? 'true' : 'false'}</dd>
            <dt>Customer</dt>
            <dd>{wishListEntity.customer ? wishListEntity.customer.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/wish-list" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/wish-list/${wishListEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ wishList }: IRootState) => ({
  wishListEntity: wishList.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(WishListDetail);
