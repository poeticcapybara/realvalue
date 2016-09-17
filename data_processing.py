import pandas as pd
import numpy as np
import sys
import os, pickle
from sklearn.linear_model import LinearRegression
from sklearn import preprocessing


from numpy import sin,cos,arctan2,sqrt,pi


EARTHRADIUS = 6371.0
fname1 = 'POIEurope.csv'


def POI_switzerland(fname1):
    data = pd.read_csv(fname1, header='infer')
    with_both = data[data.amenity.notnull() & data.name.notnull()]
    schweiz = with_both[(6 < with_both.Longitude) & (with_both.Longitude < 10.5) & (45.8 < with_both.Latitude) & (
    with_both.Latitude < 47.8)]
    return schweiz


def getDistanceByHaversine(loc1, loc2):
    '''Haversine formula - give coordinates as a 2D numpy array of
    (lat_denter link description hereecimal,lon_decimal) pairs'''
    #
    # "unpack" our numpy array, this extracts column wise arrays
    lat1 = float(loc1[1])
    lon1 = float(loc1[0])
    lat2 = float(loc2[1])
    lon2 = float(loc2[0])
    #
    # convert to radians ##### Completely identical
    lon1 = lon1 * pi / 180.0
    lon2 = lon2 * pi / 180.0
    lat1 = lat1 * pi / 180.0
    lat2 = lat2 * pi / 180.0
    #
    # haversine formula #### Same, but atan2 named arctan2 in numpy
    dlon = lon2 - lon1
    dlat = lat2 - lat1
    a = (sin(dlat/2))**2 + cos(lat1) * cos(lat2) * (sin(dlon/2.0))**2
    c = 2.0 * arctan2(sqrt(a), sqrt(1.0-a))
    km = EARTHRADIUS * c
    return km


def regression(X,y):
    clf = LinearRegression()
    clf.fit(X, y)
    return clf


def closest_n(n=5):
    pkl_file = open('nn_matrix.pkl', 'rb')
    dist = pickle.load(pkl_file)
    max_n = np.apply_along_axis(np.argpartition(dist, -n)[-n:])
    return max_n

def main(fname):
    data = pd.read_csv(fname, header='infer')
    feature_names = ['agencyId', 'score', 'balcony', 'surfaceLiving', 'numberRooms', 'city']
    label_names = ['price']

    df = pd.read_csv(fname, index_col=[0])
    sub = df[feature_names + label_names].dropna().drop_duplicates()
    if not os.path.exists('geoLoc.csv'):
        df['Lon'] = df.geoLocation.apply(lambda x: x.split(',')[0])
        df['Lat'] = df.geoLocation.apply(lambda x: x.split(',')[1])
        df[feature_names + label_names + ['Lon', 'Lat']].dropna().to_csv('geoLoc.csv')


    X = sub[feature_names].values
    le1 = preprocessing.LabelEncoder()
    le2 = preprocessing.LabelEncoder()
    le1.fit(sub['agencyId'].values)
    le2.fit(sub['city'].values)
    X[:, 0] = le1.transform(sub['agencyId'].values)
    X[:, -1] = le2.transform(sub['city'].values)
    Y = sub[label_names].values

    classifier = regression(X, Y)

    pois = POI_switzerland(fname1)
    categories = ['family', 'diversion']
    disco = pois[pois.amenity == 'nightclub']
    kids = pois[pois.amenity.isin(['school', 'kindergarten'])]



if __name__ == '__main__':
    if len(sys.argv) > 1:
        fname2 = sys.argv[1]
    else:
        fname2 = 'homegate.csv'
    main(fname2)